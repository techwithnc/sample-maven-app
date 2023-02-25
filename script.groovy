def packApp(){
    sh "mvn build-helper:parse-version versions:set -DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.minorVersion}.\${parsedVersion.nextIncrementalVersion} versions:commit"
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
def pushApp(){
    sh 'docker push techwithnc/image:5.0'
}
return this