FROM jenkins/jenkins:lts-jdk11
RUN jenkins-plugin-cli -p workflow-aggregator git ws-cleanup configuration-as-code job-dsl
COPY --chown=jenkins:jenkins jenkins.yaml /var/jenkins_home/jenkins.yaml
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV PLUGINS_FORCE_UPGRADE=true
USER root
RUN apt-get update
RUN apt-get install ansible vim -y
USER jenkins