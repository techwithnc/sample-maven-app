pipeline {
    agent any
    tools{
        maven '01Maven'
    }
    stages{
        stage("Package"){
            steps {
                sh 'ls -l'
                sh "mvn package"
                sh 'ls -l target/'
            }
        }
        stage("Build"){
            steps {
                sh 'docker image ls'
                sh 'docker build -t myimage/app:2.0 .'
                sh 'docker image ls'
            }
        }  
        stage("Docker Login") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-token', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                    sh 'echo $PASS | docker login -u $USER --password-stdin'   
                    sh "login successful"
                }
            }
        }
    }
}
 