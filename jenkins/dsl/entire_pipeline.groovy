pipeline {
  agent any

  stages {
    stage('Configure SSH Keys') {
      steps {
        build '01_ssh_configuration'
      }
  }

    stage('Build docker images'){
        parallel {
            stage('Launch Backend CI'){
                steps {
                    build '02_lior_backend'
                }
            }

            stage('Launch Frontend CI'){
                steps {
                    build '03_lior_frontend'
                }
            }
        }
    }

    stage('Deploy Lior on K3S cluster'){
        steps {
            build '04_deploy'
        }
    }

}
  post {
      always{
      cleanWs()
      }
  }
}