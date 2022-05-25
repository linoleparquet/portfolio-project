# Get admin password

docker exec jenkins_controller cat /var/jenkins_home/secrets/initialAdminPassword

# TODO:

- Fix security issue: nonVerifyingKeyVerificationStrategy
- use ssh-keygen dynamically to create the ssh_key_pair folder
