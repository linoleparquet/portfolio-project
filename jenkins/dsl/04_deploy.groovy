pipeline {
  agent any

  environment {
      ANSIBLE_CONFIG   = 'ansible/ansible.cfg'
      INVENTORY        = 'ansible/inventory/hosts.ini'
      PLAYBOOK         = 'ansible/00_deploy-lior.yaml'
  }

  stages {
    stage('Init') {
      steps {
        // Get code from the GitHub repository
        git url:'https://github.com/linoleparquet/portfolio-project', branch: 'main'
        sh "ansible-galaxy install -r ansible/collections/requirements.yml"
      }
  }

    stage('Deploy'){
        steps {
            sh "ANSIBLE_CONFIG=${env.ANSIBLE_CONFIG} ansible-playbook -i ${env.INVENTORY} ${env.PLAYBOOK}"
        }
    }

}
  post {
      always{
      cleanWs()
      }
  }
}