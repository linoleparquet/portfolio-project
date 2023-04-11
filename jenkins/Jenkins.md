# Jenkins

## Jenkins as Code

The plugin JasC load the automatically load the configuration of Jenkins at his start up.
The configuration is stored in the jenkins.yaml file, loaded in the Jenkins controller container.
This automate the action of inserting credentials throught the UI, adding an Agent, define the number of jenkins executors, etc.

## DSL Jobs

The DSL Job plugin allows the pipeline to be automatically created.
The first job, called job_dsl, is defined in the jenkins.yaml file. It execute what's defined in the job_dsl.groovy file, which consit on creating the others pipelines.

## Pipeline executions

Only one pipeline is meant to be launched by hand: `entire_pipeline`. This job encapsulate the other ones.

## TODO:

- Use Jenkins agent
