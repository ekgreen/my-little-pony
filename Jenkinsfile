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
      	    sh 'mvn ${env.MAVEN_PACKAGE_OPTIONS} clean package'
        }
    }
    stage('Docker Build & Local Deploy') {
        agent any
        steps {
            script {
                try {
                    // Build image to local repository
                    sh 'docker build \
                    --tag ${env.IMAGE_NAME}:${env.BUILD_NUMBER} \
                    --build-arg JAR_FILE=target/uber/uber-*.jar \
                    --file build/docker/Dockerfile .'

                    // Tag image to local registry
                    sh 'docker tag ${env.IMAGE_NAME}:${env.BUILD_NUMBER} registry:5000/${env.IMAGE_NAME}:${env.BUILD_NUMBER}'

                    // Push to local registry
                    sh 'docker push registry:5000/${env.IMAGE_NAME}:${env.BUILD_NUMBER}'
                } finally {
                    sh '''#!/bin/bash
                        if [[ "$(docker images -q ${env.IMAGE_NAME}:${env.BUILD_NUMBER} 2> /dev/null)" != "" ]]; then
                            docker rmi -f $(docker images -q ${env.IMAGE_NAME}:${env.BUILD_NUMBER})
                        fi
                    '''
                }
            }
        }
    }
  }
}