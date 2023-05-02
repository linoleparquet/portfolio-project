pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        // Get code from the GitHub repository
        checkout([$class: 'GitSCM', 
          branches: [[name: 'refs/tags/v1.1.1']], 
          userRemoteConfigs: [[url: 'https://github.com/linoleparquet/lior-frontend']]])
      }
  }

    stage('Run test'){
      steps{
        sh 'echo Volkswagen quality tests'
      }
    }

    stage('Build the Docker image'){
      steps{
        sh 'docker build . -t registry:5000/lior-frontend:v1.1.1'
      }
    }


    stage('Push to the registry'){
      steps {
        sh 'docker push registry:5000/lior-frontend:v1.1.1'
      }
    }

}
  post {
      always{
      cleanWs()
      }
  }
}
