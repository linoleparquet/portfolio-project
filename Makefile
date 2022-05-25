.DEFAULT_GOAL := help

# Generate a self-documented Makefile. Thanks to: https://marmelab.com/blog/2016/02/29/auto-documented-makefile.html
help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

provision_vm: ## Provision VMs defined in the Vagrantfile
	vagrant up

destroy_vm: ## Destroy VMs defined in the Vagrantfile
	vagrant destroy -f

generate_ssh_key_pair: ## Generate dynamic ssh key pair used by Jenkins to connect to his slaves
	@echo 'Creating ssh key pair. Setting them in the appropriate files... '
	@ssh-keygen -b 2048 -t rsa -f jenkins_controller -q -N ""
	@sed -i "s%JENKINS_AGENT_SSH_PUBKEY=.*%JENKINS_AGENT_SSH_PUBKEY=$$(cat jenkins_controller.pub)%" docker-compose.yaml
	@mv jenkins_controller jenkins/controller/jenkins_controller.key
	@rm jenkins_controller.pub
	@echo 'Done'

jenkins_local: generate_ssh_key_pair ## Deploy a Jenkins contoller and agent on the local machine
	docker-compose build --no-cache
	docker-compose up -d

clear: ## Cleaning the transitionnal files created and modifications applied.
	@rm -f jenkins/controller/jenkins_controller.key
	@sed -i "s%JENKINS_AGENT_SSH_PUBKEY=.*%JENKINS_AGENT_SSH_PUBKEY= #auto generated private key will come here%" docker-compose.yaml