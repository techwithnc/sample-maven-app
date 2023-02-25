def buildApp(){
    sh 'ls -l'
    sh 'docker build -t techwithnc/image:5.0 .'
    sh 'docker image ls'
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