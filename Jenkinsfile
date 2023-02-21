#!groovy

pipeline {
  agent none
  stages {
  	stage('Maven Install') {
    	agent {
      	docker {
        	image '3.9.0-eclipse-temurin-19'
        }
      }
      steps {
      	sh 'mvn clean install'
      }
    }
  }
}