pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        // Get some code from a GitHub repository
        git 'https://github.com/linoleparquet/lior-backend'
        sh ' docker build . -t registry:5000/lior-backend'
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