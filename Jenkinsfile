#!groovy

pipeline {
  agent none
  stages {
  	stage('Maven Install') {
    	agent {
      	    docker {
        	    image 'maven:3.9.0-eclipse-temurin-19'
        	    args '-u root'
            }
        }
        steps {
      	    sh 'mvn clean install'
        }
    }
    stage('Docker Build') {
        agent any
        steps {
            try {
                // Build image to local repository
                sh 'docker build \
                --tag my-little-pony-service:1 \
                --build-arg JAR_FILE=target/uber/uber-*.jar \
                --file build/docker/Dockerfile .'

                // Tag image to local registry
                sh 'docker tag my-little-pony-service:1 registry:5000/my-little-pony-service:1'

                // Push to local registry
                sh 'docker push registry:5000/my-little-pony-service:1'
            } finally {
                sh '''
                if [[ "$(docker images -q my-little-pony-service:1 2> /dev/null)" != "" ]]; then
                  docker rmi -f $(docker images -q my-little-pony-service:1)
                fi
                '''
            }
        }
    }
  }
}