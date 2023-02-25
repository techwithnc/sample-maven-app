def incrementApp() {
    sh 'mvn build-helper:parse-version versions:set \
        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
        versions:commit'

}
def packApp(){
    sh "cat pom.xml | grep 1.1 | grep version"
    sh "mvn clean package"

}
def buildApp() {
    sh 'docker build -t techwithnc/simple-maven-app:1.0 .'
}
def loginApp(){
    withCredentials([usernamePassword(credentialsId: 'dockerhub-token', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
                        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
                    }
}
def loginGithub(){
    withCredentials([usernamePassword(credentialsId: 'github-ssh', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
                        sh 'git config user.email jenkins@techwithnc.com'
                        sh 'git config user.name jenkins'
                        sh 'git status'
                        sh 'git branch'
                        sh 'git config --list'
                        sh "git remote set-url origin https://${USERNAME}:${PASSWORD}@github.com:techwithnc/simple-java-maven-app.git"
                        sh 'git add .'
                        sh 'git commit -m "update app version"'
                        sh 'git push origin HEAD:main'
                    }

}
def pushApp(){
    sh 'docker push techwithnc/image:5.0'
}
return this