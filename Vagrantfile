# -*- mode: ruby -*-
# vi: set ft=ruby :

ENV['VAGRANT_DEFAULT_PROVIDER'] = 'libvirt'

Vagrant.configure("2") do |config|

  # Remove the folder name prefix in front of the vm machine name 
  config.vm.provider :libvirt do |libvirt|
    libvirt.default_prefix = ""
  end

  # CI/CD
  config.vm.define :cicd do |c|
    c.vm.box = "generic/ubuntu2004"
    c.vm.hostname = "cicd"
    c.vm.network "private_network", ip: "192.168.33.10"
    c.vm.provision "file", source: "vagrant/docker-compose.yaml", destination: "$HOME/docker-compose/docker-compose.yaml"
    c.vm.provision "file", source: "jenkins/controller", destination: "$HOME/docker-compose/jenkins/controller"
    c.vm.provision "shell", path: "vagrant/configure_cicd.sh"
  end
end
