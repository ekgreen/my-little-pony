#!groovy

pipeline {
  agent none
  stages {
  	stage('Maven Build') {
    	agent {
      	    docker {
        	    image 'maven:3.9.0-eclipse-temurin-19'
        	    args '-u root'
            }
        }
        steps {
            script {
                def options = params.MAVEN_PACKAGE_OPTIONS ? "${params.MAVEN_PACKAGE_OPTIONS}" : ""

      	        sh "mvn ${options} clean package"
            }
        }
    }
    stage('Docker Build & Local Deploy') {
        agent any
        steps {
            script {
                def imageName = "${params.IMAGE_NAME}:${env.BUILD_NUMBER}"
                echo "Image name: ${imageName}"

                try {
                    // Build image to local repository
                    sh "docker build                                    \
                        --tag ${imageName}                              \
                        --build-arg JAR_FILE=target/uber/uber-*.jar     \
                        --file build/docker/Dockerfile ."

                    // Tag image to local registry
                    sh "docker tag ${imageName} registry:5000/${imageName}"

                    // Push to local registry
                    sh "docker push registry:5000/${imageName}"
                } finally {
                    sh '''#!/bin/bash
                        if [[ "$(docker images -q ${imageName} 2> /dev/null)" != "" ]]; then
                            docker rmi -f $(docker images -q ${imageName})
                        fi
                    '''
                }
            }
        }
    }
  }
}