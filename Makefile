.DEFAULT_GOAL := help

# Generate a self-documented Makefile. Thanks to: https://marmelab.com/blog/2016/02/29/auto-documented-makefile.html
help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

provision_vm: ## Provision VMs defined in the Vagrantfile
	vagrant up

destroy_vm: ## Destroy VMs defined in the Vagrantfile
	vagrant destroy -f

deploy_lior: ## Deploy the K3s cluster, Argo, and Lior as an Argo application using Jenkins
	curl -X POST http://192.168.33.10/job/job_dsl/build
	sleep 10
	curl -X POST http://192.168.33.10/job/01_ssh_configuration/build
	sleep 10
	curl -X POST http://192.168.33.10/job/04_deploy/build