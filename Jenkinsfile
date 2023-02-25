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
                sh 'docker build -t myimage/app:1.0 .'
                sh 'docker image ls'
            }
        }        
    }
}
 