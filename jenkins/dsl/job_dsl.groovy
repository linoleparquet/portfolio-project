pipelineJob('01_ssh_configuration') {
    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/01_ssh_configuration.groovy'))
        }
    }
}

pipelineJob('02_lior_backend') {
    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/02_lior_backend.groovy'))
        }
    }
}

pipelineJob('03_lior_frontend') {
    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/03_lior_frontend.groovy'))
        }
    }
}

pipelineJob('04_deploy') {
    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/04_deploy.groovy'))
        }
    }
}

pipelineJob('entire_pipeline') {
    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/entire_pipeline.groovy'))
        }
    }
}