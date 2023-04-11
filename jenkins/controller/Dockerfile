FROM jenkins/jenkins:lts-jdk11
RUN jenkins-plugin-cli -p workflow-aggregator git ws-cleanup configuration-as-code job-dsl permissive-script-security
COPY --chown=jenkins:jenkins jenkins.yaml /var/jenkins_home/jenkins.yaml
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV PLUGINS_FORCE_UPGRADE=true
USER root
RUN apt-get update
RUN apt-get install ansible vim -y
# TODO: Refacto the usage of ssh-copy-id to get rid of sshpass
RUN apt-get install sshpass -y
USER jenkins