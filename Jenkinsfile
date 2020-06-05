pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Initiating maven build'
        sh 'mvn clean install -Dlicense.skip=true'
        echo 'Maven build complete'
      }
    }

    stage('Testing') {
      parallel {
        stage('Test') {
          steps {
            sh 'mvn clean test'
          }
        }

        stage('Print Tester credentials') {
          steps {
            sleep 10
            echo "The tester is ${TESTER}"
          }
        }

        stage('Print Build Number') {
          steps {
            sleep 20
            echo "This is build number ${BUILD_ID}"
          }
        }

      }
    }

    stage('Deploy prompt') {
      steps {
        input 'Deploy to Production?'
      }
    }

    stage('Deploy') {
      steps {
        echo 'Initiating Deployment'
        echo 'Deployment Complete'
      }
    }

  }
  tools {
    maven 'Maven 3.6.3'
  }
  environment {
    TESTER = 'placeholder'
  }
}