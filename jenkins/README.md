<p>
    <img
      src="https://www.vectorlogo.zone/logos/jenkins/jenkins-icon.svg"
      alt="jenkins"
      width="40"
      height="40"
      align="right"
    />
</p>

# Jenkins

Jenkins run on the CI/CD Machine as a Docker container.  
The Dockerfile can be found under `jenkins/controller/Dockerfile`

## JobDSL configuration

This project leverage the JobDSL plugin.  
The file `jenkins/dsl/job_dsl.groovy` contains the definition of the pipelines to load.

## Jenkins Infrastrucutre as Code

The plugin JasC load the automatically load the configuration of Jenkins at his start up.  
The configuration is stored in the jenkins.yaml file, loaded in the Jenkins controller container.  
This automate the action of inserting credentials throught the UI, adding an Agent, define the number of jenkins executors, etc.  
The configuration file is called `jenkins/controller/jenkins.yaml`. It is copied on the jenkins docker image at buildtime.

## Pipelines

Only the pipeline `entire_pipeline` is meant to be triggered manually. This job encapsulate the other ones.

### Definitions

| Name                 | Description                                                                                                                                                                   |
| -------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 01_ssh_configuration | Setup the environment SSH-wise: Configure the jenkins container to send SSH command to the kubernetes cluster machines                                                        |
| 02_lior_backend      | Test, build, and push the backend image to the local registry                                                                                                                 |
| 03_lior_frontend     | Test, build, and push the frontend image to the local registry                                                                                                                |
| 04_deploy            | Deploy K3s on the kubernetes cluster machines. Then, deploy ArgoCD on top of it. Then ArgoCD pull the lior helm chart, and deploy it. This is done using an Ansible playbook. |
| entire_pipeline      | Wrap up the previous pipelines. This is the only pipeline supposed to be triggered manually                                                                                   |

### Trigger a Jenkins build throught HTTP

curl -X POST http://192.168.33.10/job/job_dsl/build  
curl -X POST http://192.168.33.10/job/01_ssh_configuration/build  
curl -X POST http://192.168.33.10/job/04_deploy/build

This can be done because CSRF Protection is disabled for simplicity
