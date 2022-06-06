# Recreate the machine and SSH into it

vagrant destroy -f;
vagrant up;

ssh-keygen -f "/home/lino/.ssh/known_hosts" -R "192.168.33.11";
ssh-keygen -f "/home/lino/.ssh/known_hosts" -R "192.168.33.12";
ssh-keygen -f "/home/lino/.ssh/known_hosts" -R "192.168.33.13";

sshpass -p vagrant ssh-copy-id vagrant@192.168.33.11;
sshpass -p vagrant ssh-copy-id vagrant@192.168.33.12;
sshpass -p vagrant ssh-copy-id vagrant@192.168.33.13;

# Pre-requises:

# Ansible configuration

move group vars in to inventory/group_vars

# Deploy argo

ansible-galaxy install -r ansible/collections/requirements.yml

ANSIBLE_CONFIG=/ansible/ansible.cfg ansible-playbook -i ansible/inventory/hosts.ini ansible/00_deploy-lior.yaml
