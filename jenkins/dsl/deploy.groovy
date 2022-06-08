pipeline {
  agent any

  stages {
    stage('Init') {
      steps {
        // Get some code from a GitHub repository
        git 'https://github.com/linoleparquet/portfolio-project'
        sh 'ansible-galaxy install -r portfolio-project/ansible/collections/requirements.yml'
      }
  }

    stage('Clees SSH') {
      steps {
          sh ''
      }
  }

    stage('Deploy'){
        steps {
            sh 'ANSIBLE_CONFIG=portfolio-project/ansible/ansible.cfg ansible-playbook -i portfolio-project/ansible/inventory/hosts.ini portfolio-project/ansible/00_deploy-lior.yaml'
        }
    }

}
  post {
      always{
      cleanWs()
      }
  }
}