def mygvscript
pipeline {
    agent any
    environment {
        IMAGE_NAME = ""
        APP_VERSION = ""
    }
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
        stage("Build_App"){
            steps{
                script{
                    mygvscript.buildApp()
                    APP_VERSION = readMavenPom().getVersion()
                    echo "VERSION: ${APP_VERSION}"
                    IMAGE_NAME = "${APP_VERSION}-${env.BUILD_ID}"
                    echo "IMAGE_NAME: ${IMAGE_NAME}"
                }
            }
        }
        stage("Build_IMAGE"){
            steps {
                script {
                    mygvscript.buildImage()
                }
            }
        }
        // stage("Build_IMAGE"){
        //     steps {
        //         script {
        //             mygvscript.buildImage()
        //         }
        //     }
        // }  
        // stage("Push_IMAGE") {
        //     steps {
        //         script {
        //             mygvscript.pushImage()
        //         }
        //     }
        // }
    }
}
 