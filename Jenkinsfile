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
            sh 'docker build \
                --tag my-little-pony-service:1 \
                --build-arg JAR_FILE=target/uber/uber-*.jar \
                --file build/docker/Dockerfile .'
        }
    }
  }
}