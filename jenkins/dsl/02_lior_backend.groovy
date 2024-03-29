pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        // Get code from the GitHub repository
        checkout([$class: 'GitSCM', 
          branches: [[name: 'refs/tags/v1.0.0']], 
          userRemoteConfigs: [[url: 'https://github.com/linoleparquet/lior-backend']]])

      }
  }

    stage('Run test'){
      steps{
        sh 'echo Volkswagen quality tests'
      }
    }

    stage('Build the Docker image'){
      steps{
        sh 'docker build . -t registry:5000/lior-backend:v1.0.0'
      }
    }


    stage('Push to the registry'){
      steps {
        sh 'docker push registry:5000/lior-backend:v1.0.0'
      }
    }

}
  post {
      always{
      cleanWs()
      }
  }
}
