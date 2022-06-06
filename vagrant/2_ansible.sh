echo 'START - Install Ansible'

echo '[1] - Install Ansible'
sudo add-apt-repository --yes --update ppa:ansible/ansible &>/dev/null
sudo apt-get install software-properties-common ansible -y &>/dev/null

: '

############### Toute cette partie est a executer avec le user vagrant. 


touch /home/vagrant/.ssh/known_hosts
ssh-keyscan -H -t rsa 192.168.33.11 >> /home/vagrant/.ssh/known_hosts
ssh-keyscan -H -t rsa 192.168.33.12 >> /home/vagrant/.ssh/known_hosts
ssh-keyscan -H -t rsa 192.168.33.13 >> /home/vagrant/.ssh/known_hosts


# Create SSH key pairs
ssh-keygen -q -t rsa -N '' <<< $'\ny' >/dev/null 2>&1

# Add in authorized key
# Super insecure. Fix this sshpass hack with terraform
sudo apt-get install sshpass &>/dev/null
sshpass -p vagrant ssh-copy-id vagrant@192.168.33.11 
sshpass -p vagrant ssh-copy-id vagrant@192.168.33.12 
sshpass -p vagrant ssh-copy-id vagrant@192.168.33.13

ansible-galaxy install -r ansible/collections/requirements.yml &>/dev/null
ANSIBLE_CONFIG=/ansible/ansible.cfg ansible-playbook -i ansible/inventory/hosts.ini ansible/00_deploy-lior.yaml
'
echo 'END - Install Ansible'