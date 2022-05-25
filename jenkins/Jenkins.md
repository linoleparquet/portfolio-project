# Get admin password

docker exec jenkins_controller cat /var/jenkins_home/secrets/initialAdminPassword

# TODO:

- Fix security issue: nonVerifyingKeyVerificationStrategy
- use ssh-keygen dynamically to create the ssh_key_pair folder
- run agent_01 container as jenkins user. Hint: https://stackoverflow.com/questions/54452152/run-docker-as-jenkins-agent-in-a-docker-container-as-non-root-user
