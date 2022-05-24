# SSH into the Vagrant machine

## Unsafe way: using the password

`ssh vagrant@192.168.33.10`. SSH connection will ask you for the password. Type `vagrant`.
Each time you want to connect to the vagrant machine, the password will be requiered

## Following best practice: add your host to the machine

`ssh-copy-id vagrant@192.168.33.10`. SSH connection will ask you for the password. Type `vagrant`.
Each following connection to the Vagrant machine dont need any password. Just `ssh vagrant@192.168.33.10`

# Recreate the machine and SSH into it

`ssh-keygen -f "/home/lino/.ssh/known_hosts" -R "192.168.33.10"; ssh-copy-id vagrant@192.168.33.10`

# Run the playbook

`ansible-playbook playbook.yml`
