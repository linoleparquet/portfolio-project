pipeline {
  agent any

  environment {
      ANSIBLE_CONFIG   = 'ansible/ansible.cfg'
      INVENTORY        = 'ansible/inventory/hosts.ini'
      PLAYBOOK         = 'ansible/00_deploy-lior.yaml'
  }

  stages {
    stage('Downloading Ansible collections requierements ') {
      steps {
        echo ""
        // Get code from the GitHub repository
        git url:'https://github.com/linoleparquet/portfolio-project', branch: 'main'
        sh "ansible-galaxy install -r ansible/collections/requirements.yml"
      }
  }

    stage('Running Ansible playbook'){
        steps {
            sh "ANSIBLE_CONFIG=${env.ANSIBLE_CONFIG} ansible-playbook -i ${env.INVENTORY} ${env.PLAYBOOK}"
            echo "The application Lior is deployed on the k3s cluster."
            echo "It's accessible on 192.168.33.11, 192.168.33.12 or 192.168.33.13"
        }
    }

}
  post {
      always{
      cleanWs()
      }
  }
}