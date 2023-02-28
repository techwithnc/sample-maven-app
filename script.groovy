def buildApp() {
        sh 'mvn build-helper:parse-version versions:set \
        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
        versions:commit'
        sh "mvn clean package"
}
def buildImage(){
    APP_VERSION = readMavenPom().getVersion()
    IMAGE_NAME = "${APP_VERSION}-${env.BUILD_ID}"
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
                        sh "git remote set-url origin https://${USERNAME}:${PASSWORD}@github.com/techwithnc/simple-java-maven-app.git"
                        sh 'git add .'
                        sh 'git commit -m "update app version"'
                        sh 'git push origin HEAD:main'
    }
}
return this