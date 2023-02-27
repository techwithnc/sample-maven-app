def buildApp() {
    sh 'mvn build-helper:parse-version versions:set \
        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
        versions:commit'
}
// def buildApp(){
//     sh "cat pom.xml | grep 1.1 | grep version"
//     sh "mvn clean package"
}
def buildImage(){
    withCredentials([usernamePassword(credentialsId: 'dockerhub-token', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
                        matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                        version = matcher[0][1]
                        IMAGE_NAME = "$version-$BUILD_NUMBER"
                        sh "echo ${env.IMAGE_NAME}"
                        sh "docker build -t techwithnc/simple-java-app:$IMAGE_NAME ."
                        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
                        sh "docker push techwithnc/simple-java-app:$IMAGE_NAME"
                    }
}
def pushImage(){
    sh "docker push techwithnc/simple-java-app:$IMAGE_NAME"
}
return this