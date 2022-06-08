pipeline {
  agent any

  environment {
      IPS = '192.168.33.11,192.168.33.12,192.168.33.13'
  }

  stages {
    stage('Initialise SSH Keys') {
      steps {
        sh """
        if [ ! -f $HOME/.ssh/id_rsa ]; then
            ssh-keygen -t rsa -N '' -f $HOME/.ssh/id_rsa
        else
            echo "SSH keys already exists"
        fi
        """
      }
  }

    stage('Store public keys of nodes of the cluster') {
      steps {
        // Create the known_hosts file, if he doesnt already exist 
        sh """
        if [ ! -f $HOME/.ssh/known_hosts ]; then
            if [ ! -d $HOME/.ssh ]; then
              mkdir $HOME/.ssh
            fi
            touch $HOME/.ssh/known_hosts
        fi
        """
        // Retreive the public key of each node of the cluster, and all it to the known_hosts file
        // This enshure no blueprint confirmation is prompted during first ssh connection 
        
        script{
          IPS.tokenize(',').each{
            sh "ssh-keyscan -H -t rsa ${it} >> $HOME/.ssh/known_hosts"
          }
        }
      }
  }

    stage('Install cicd server ssh key on remote machine as a authorized key') {
      // Super insecure. Fix this sshpass hack with terraform or vault
      steps {
        withCredentials([usernamePassword(credentialsId: 'vagrant_machine_credentials', passwordVariable: 'USER', usernameVariable: 'PASSWORD')]) {
          script{
              sh 'sshpass -p $PASSWORD ssh-copy-id $USER@192.168.33.11'
              sh 'sshpass -p $PASSWORD ssh-copy-id $USER@192.168.33.12'
              sh 'sshpass -p $PASSWORD ssh-copy-id $USER@192.168.33.13'
          }
        }
      }
    }

}
  post {
      always{
      cleanWs()
      }
  }
}