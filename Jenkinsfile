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
  }
}