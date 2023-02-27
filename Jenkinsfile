def mygvscript
def matcher
def version
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
                    // matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    // version = matcher[0][1]
                    // env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                    // sh "echo ${env.IMAGE_NAME}"
                }
            }
        }
        stage("Build_App"){
            steps {
                script {
                    mygvscript.buildApp()
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
 