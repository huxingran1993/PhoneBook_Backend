pipeline {
  agent {
    docker {
      image 'maven:3.5.2-jdk-8-alpine'
      args '-v /root/.m2:/root/.m2'
    }

  }
  stages {
    stage('Compile, build and docker image') {
      steps {
        sh 'mvn -B -DskipTests clean package'
      }
    }

    stage('Unit Test') {
      post {
        always {
          junit 'target/surefire-reports/*.xml'
        }

      }
      steps {
        sh 'mvn test'
      }
    }

    stage('Push to DockerHub') {
      steps {
        sh './jenkins/scripts/deliver.sh'
        sh 'mvn dockerfile:push'
      }
    }

  }
}