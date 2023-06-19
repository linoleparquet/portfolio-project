<p>
    <img
        src="https://upload.wikimedia.org/wikipedia/commons/2/24/Ansible_logo.svg"
        alt="Ansible"
        width="40"
        height="40"
        align="right"
    />
</p>

# Ansible

## Inventory

Two groups are defined in the inventory: the master group and the node group.  
They represent the master and the node of a k8s cluster.

## Playbook

The main playbook is called `00_deploy-lior.yaml`.  
It install K3S on a cluster of 3 machines, deploy ArgoCD on top of it, and deploy Lior as an Argo Application.  
The sum of thoses actions lead to the deployment of Lior of 3 completely new and fresh ubuntu machines.

## Roles

| Name     | Origin                                               | Definition                                                                 |
| -------- | ---------------------------------------------------- | -------------------------------------------------------------------------- |
| argo     | custom                                               | Deploy [ArgoCD](https://github.com/argoproj/argo-cd) on a k8s cluster      |
| download | [k3s-ansible](https://github.com/k3s-io/k3s-ansible) | Download k3s binaires                                                      |
| k3s      | [k3s-ansible](https://github.com/k3s-io/k3s-ansible) | Enable K3S as a Service on the master node, and configure the slaves nodes |
| lior     | custom                                               | Deploy Lior on a k8s cluster, as an Argo Application                       |
| prereq   | [k3s-ansible](https://github.com/k3s-io/k3s-ansible) | Check the prerequise to install K3S                                        |
| reset    | [k3s-ansible](https://github.com/k3s-io/k3s-ansible) | Uninstall K3s                                                              |
