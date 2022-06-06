# -*- mode: ruby -*-
# vi: set ft=ruby :

ENV['VAGRANT_DEFAULT_PROVIDER'] = 'libvirt'

Vagrant.configure("2") do |config|

  # # Remove the folder name prefix in front of the vm machine name 
  # config.vm.provider :libvirt do |libvirt|
  #   libvirt.default_prefix = ""
  # end

  # DEV-MASTER
  config.vm.define "dev-master" do |c|
    c.vm.box = "generic/ubuntu2004"
    c.vm.hostname = "dev-master"
    c.vm.network "private_network", ip: "192.168.33.11"
  end

  # DEV-NODE-01
  config.vm.define "dev-node-01" do |c|
    c.vm.box = "generic/ubuntu2004"
    c.vm.hostname = "dev-node-01"
    c.vm.network "private_network", ip: "192.168.33.12"
  end
  
  # DEV-NODE-02
  config.vm.define "dev-node-02" do |c|
    c.vm.box = "generic/ubuntu2004"
    c.vm.hostname = "dev-node-02"
    c.vm.network "private_network", ip: "192.168.33.13"
  end

  # CI/CD
  config.vm.define :cicd do |c|
    c.vm.box = "generic/ubuntu2004"
    c.vm.hostname = "cicd"
    c.vm.network "private_network", ip: "192.168.33.10"

    # Jenkins and Docker
    c.vm.provision "file", source: "vagrant/docker-compose.yaml", destination: "$HOME/docker-compose/docker-compose.yaml"
    c.vm.provision "file", source: "jenkins/controller", destination: "$HOME/docker-compose/jenkins/controller"
    c.vm.provision "shell", path: "vagrant/1_docker_jenkins.sh"

    # Ansible 
    c.vm.provision "file", source: "ansible", destination: "$HOME/ansible"
    c.vm.provision "shell", path: "vagrant/2_ansible.sh"

  end
  
end
