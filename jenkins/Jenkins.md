# Get admin password

docker exec jenkins_controller cat /var/jenkins_home/secrets/initialAdminPassword

# How to build the images

To build the frontend image, you need to manually create a new jenkins item called "Pipeline". You can name it as you want. Let's call it `lior-frontend`.
Reach the pipeline tab. Copy paste the content of the `lior-frontend/Jenkinsfile` file in the 'Script' block. Save.
Click on "Build Now".
The corresponding git project will be downloaded, build as a docker image, and pushed to the docker registry.
It will be available on the docker registry, named as `registry:5000/lior-frontend`
To pull it, call the following command: `docker pull registry:5000/lior-frontend`

Repeat thoses steps to build the lior-backend image, with the content of `lior-backend/Jenkinsfile`

# TODO:

- Tend on a unique reusable pipeline for front and back end build
- Use Jenkinsfile variables: registry, name, version of the build
- Automate the creation of the Jenkins pipelines
- Use Jenkins agent
