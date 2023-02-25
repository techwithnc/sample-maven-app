pipeline {
    agent any
    tools{
        maven '01Maven'
    }
    stages{
        stage("build"){
            steps {
                sh 'ls -l'
                sh "mvn package"
                sh 'ls -l target/'
            }
        }        
    }
}
 