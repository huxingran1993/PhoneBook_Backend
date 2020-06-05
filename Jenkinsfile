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

    stage('Building image') {
      steps {
        script {
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }

      }
    }

    stage('Deploy Image') {
      steps {
        script {
          docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
          }
        }

      }
    }

    stage('Remove Unused docker image') {
      steps {
        sh "docker rmi $registry:$BUILD_NUMBER"
      }
    }

  }
  tools {
    maven 'Maven 3.6.3'
  }
  environment {
    TESTER = 'placeholder'
    registry = 'docker_hub_account/repository_name'
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
}