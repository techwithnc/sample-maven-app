pipeline {
    agent any
    tools{
        maven '01Maven'
    }
    stages{
        stage("Package"){
            steps {
                sh "mvn clean package"
            }
        }
        stage("Build"){
            steps {
                sh 'docker build -t myimage/app:2.0 .'
            }
        }  
        stage("Docker Login") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-token', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                    sh 'echo $PASS | docker login -u $USER --password-stdin'
                }
            }
        }
    }
}
 