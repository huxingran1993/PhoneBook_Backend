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
        stage('SonarQube Test') {
          steps {
            echo 'Initiating SonarQube test'
            sh 'mvn sonar:sonar -Dsonar.host.url=http://<IP address>:8081 -Dlicense.skip=true'
            echo 'SonarQube test Complete'
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

  }
  tools {
    maven 'Maven 3.6.3'
  }
}