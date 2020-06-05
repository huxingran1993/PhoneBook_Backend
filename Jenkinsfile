pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests clean package'
      }
    }

    stage('Deploy') {
      steps {
        sh 'ls build/libs/*.jar'
        echo 'Deploying...'
      }
    }

  }
  triggers {
    pollSCM('* * * * *')
  }
}