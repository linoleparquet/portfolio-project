FROM jenkins/jenkins:lts-jdk11
RUN jenkins-plugin-cli -p configuration-as-code workflow-aggregator ssh-slaves
COPY --chown=jenkins:jenkins jenkins.yaml /var/jenkins_home/jenkins.yaml
COPY --chown=jenkins:jenkins jenkins_controller.key /var/jenkins_home/jenkins_controller.key