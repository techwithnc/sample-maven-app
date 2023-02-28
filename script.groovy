def buildApp() {
        sh 'mvn build-helper:parse-version versions:set \
        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
        versions:commit'
    sh "mvn clean package"
}
def buildImage(){
    sh "docker build -t techwithnc/simple-java-app:$IMAGE_NAME ."
    sh "docker image ls"
}
def pushImage(){
    withCredentials([usernamePassword(credentialsId: 'dockerhub-token', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
                        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
                        sh "docker push techwithnc/simple-java-app:$IMAGE_NAME"
                    }
}
def incrementapp(){
    withCredentials([usernamePassword(credentialsId: 'github-token', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
                        sh 'git config user.email jenkins@techwithnc.com'
                        sh 'git config user.name jenkins'
                        sh 'git status'
                        sh 'git config --list'
                        sh "git remote set-url origin https://${USERNAME}:${PASSWORD}@github.com/techwithnc/testrepo01.git"
                        sh 'git add .'
                        sh 'git commit -m "update app version"'
                        sh 'git push origin HEAD:main'
    }
}
return this