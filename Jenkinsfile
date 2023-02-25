def mygvscript
pipeline {
    agent any
    tools{
        maven '01Maven'
    }
    stages{
        stage("Prepare"){
            steps{
                script{
                    mygvscript = load "script.groovy"
                }
            }
        }
        stage("Increment_App"){
            steps{
                script{
                    mygvscript.incrementApp()
                }
            }
        }
        stage("Pack_App"){
            steps {
                script {
                    mygvscript.packApp()
                }
            }
        }
        stage("Commit_To_Github"){
            steps {
                script {
                    mygvscript.loginGithub()
                }
            }
        }
        // stage("Build_App"){
        //     steps {
        //         script {
        //             mygvscript.buildApp()
        //         }
        //     }
        // }  
        // stage("Docker_Login") {
        //     steps {
        //         script {
        //             mygvscript.loginApp()
        //         }
        //     }
        // }
    }
}
 