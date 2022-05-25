# -*- mode: ruby -*-
# vi: set ft=ruby :

ENV['VAGRANT_DEFAULT_PROVIDER'] = 'libvirt'

Vagrant.configure("2") do |config|

  # Remove the folder name prefix in front of the vm machine name 
  config.vm.provider :libvirt do |libvirt|
    libvirt.default_prefix = ""
  end

  config.vm.define :cicd do |c|
    c.vm.box = "generic/ubuntu2004"
    c.vm.network "private_network", ip: "192.168.33.10"
  end
end
