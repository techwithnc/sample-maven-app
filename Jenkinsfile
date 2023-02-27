def mygvscript
pipeline {
    agent any
    environment {
        IMAGE_NAME = ""
        MATCHER = ""
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
                    MATCHER = readFile('pom.xml') =~ '<version>(.+)</version>'
                    APP_VERSION = MATCHER[0][1]
                    IMAGE_NAME = "${env.APP_VERSION}-${env.BUILD_NUMBER}"
                    sh "echo ${env.IMAGE_NAME}"
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
 