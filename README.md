# Goal of the project

Show off technical skills

Deploy a CI/CI Jenkins Server

Steps:
Provision a clean Vagrant machine with Terraform
Automate the configuration of the provisionned machine with Ansible.

Deploy Jenkins or GitLabCI or CircleCI or wtv
Deploy an Artifactory

Deploy a Jenkins CI / CD pipeline .

The pipeline will grab the code from the Git server, security scan, unit test, push to the artifactory.
The CD will deploy the application on a Kubernetes cluster thanks to ArgoCD
`vagrant init hashicorp/bionic64`

# Vagrant

To provision the infrastructure, Make sure [Vagrant can run with libvrit on your machine](https://computingforgeeks.com/using-vagrant-with-libvirt-on-linux/).
Then run `vagrant up`
