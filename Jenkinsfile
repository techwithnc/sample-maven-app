CODE_CHANGES = getGitChanges()
pipeline {
    agent any
    parameters {
        string(name: 'VERSION', defaultValue: '', description: 'version to deploy on prod')
        choice(name: 'VERSION', choices: ['1.1.0', '1.2.0', '1.3.0'], description: '')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
    }
    tools{
        gradle
        maven 'NAME'(in plugin session)
        jdk
    }
    environment {
        NEW_VERSION = '1.3.0'
        SERVER_CREDNTIALS = credentials('Mrtest')
    }
    stages{
        stage("build"){
            when {
                expression {
                    params.executeTests == true
                }
            }
            steps {
                echo 'building the application....'
                echo "building version ${NEW_VERSION}"
                sh "mvn install"
            }
        }
        stage("test"){
            when {
                expression {
                    BRANCH_NAME == 'dev' || CODE_CHANGES == true
                }
            }
            steps {
                echo 'testing the application....'
            }
        }
        stage("deploy"){
            input {
                message "Selete the environment to deploy to"
                ok "Done"
                parameters {
                    choice(name: 'END', choices: ['dev', 'staging', 'prod'], description: '')
                }
            }
            steps {
                echo 'deploying the application....'
                echo "deploying with ${SERVER_CREDENTIALS}"
                echo "deploying version ${params.VERSION}"
                sh ${SERVER_CREDENTIALS}
                withCredentials([
                    usernamePassword(credentialsId: 'Mrtest', usernameVariable: USER, passwordVariable: PWD)
                ]) {
                    sh "some script ${USER} ${PWD}"
                }
            }
        }
        
    }
    post {
        always {
            // always 
        }
        success {
            //
        }
        failure {
            //
        }
    }
}
 