FROM jenkins/jenkins:lts-jdk11
RUN jenkins-plugin-cli -p workflow-aggregator git ws-cleanup configuration-as-code job-dsl permissive-script-security matrix-auth
COPY --chown=jenkins:jenkins jenkins.yaml /var/jenkins_home/jenkins.yaml
# Disable CSRF Protection to allow anonymous user to trigger a build
ENV JAVA_OPTS "-Dhudson.security.csrf.GlobalCrumbIssuerConfiguration.DISABLE_CSRF_PROTECTION=true -Djenkins.install.runSetupWizard=false"
ENV PLUGINS_FORCE_UPGRADE=true
USER root
RUN apt-get update
RUN apt-get install ansible vim sshpass -y
USER jenkins