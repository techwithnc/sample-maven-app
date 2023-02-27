def buildApp() {
    sh 'mvn build-helper:parse-version versions:set \
        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
        versions:commit'
    sh "mvn clean package"
}
// def buildApp(){
//     sh "cat pom.xml | grep 2.1 | grep version"
//     sh "mvn clean package"
// }
def buildImage(){
    withCredentials([usernamePassword(credentialsId: 'dockerhub-token', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
                        sh "docker build -t techwithnc/simple-java-app:$IMAGE_NAME ."
                        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
                        sh "docker push techwithnc/simple-java-app:$IMAGE_NAME"
                    }
}
// def pushImage(){
//     sh "docker push techwithnc/simple-java-app:$IMAGE_NAME"
// }
return this