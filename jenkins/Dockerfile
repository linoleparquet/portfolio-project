FROM jenkins/jenkins:lts-jdk11
RUN jenkins-plugin-cli -p configuration-as-code workflow-aggregator ssh-slaves
COPY ssh_key_pair/jenkins_controller.key /var/jenkins_home/jenkins_controller.key
COPY jenkins.yaml /var/jenkins_home/jenkins.yaml

USER root
RUN apt-get update
RUN apt-get install vim -y
USER jenkins