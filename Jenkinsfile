def mygvscript
def NEW_APP_VERSION
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
        stage("Push_IMAGE") {
            steps {
                script {
                    mygvscript.pushImage()
                }
            }
        }
        stage("Increment_APP_Version") {
            steps {
                script {
                    mygvscript.incrementapp()
                }
            }
        }
        stage("Push_Codes") {
            steps {
                script {
                    mygvscript.pushcode()
                }
            }
        }
    }
}
 