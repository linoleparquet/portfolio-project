pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        // Get code from the GitHub repository
        git 'https://github.com/linoleparquet/lior-frontend'
        sh 'docker build . -t registry:5000/lior-frontend'
      }
  }

    stage('Push'){
      steps {
        sh 'docker push registry:5000/lior-frontend'
      }
    }

}
  post {
      always{
      cleanWs()
      }
  }
}
