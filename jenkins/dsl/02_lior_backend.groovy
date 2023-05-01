pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        // Get code from the GitHub repository
        git 'https://github.com/linoleparquet/lior-backend'
      }
  }

    stage('Run unitary tests') {
      steps{
        sh 'docker run -it --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.8.1-openjdk-17-slim mvn test'
      }
    }

    stage('Run quality gates test'){
      steps{
        sh 'echo Volkswagen quality tests'
      }
    }

    stage('Build the Docker image'){
      steps{
        sh 'docker build . -t registry:5000/lior-backend'
      }
    }


    stage('Push'){
      steps {
        sh 'docker push registry:5000/lior-backend'
      }
    }

}
  post {
      always{
      cleanWs()
      }
  }
}
