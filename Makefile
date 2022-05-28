.DEFAULT_GOAL := help

# Generate a self-documented Makefile. Thanks to: https://marmelab.com/blog/2016/02/29/auto-documented-makefile.html
help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

provision_vm: ## Provision VMs defined in the Vagrantfile
	vagrant up

destroy_vm: ## Destroy VMs defined in the Vagrantfile
	vagrant destroy -f

jenkins_local: ## Deploy a Jenkins contoller and agent on the local machine
	docker-compose build --no-cache
	docker-compose up -d