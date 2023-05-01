pipelineJob('01_ssh_configuration') {
    // Enable remote builds from anonymous users
    authorization {
        permission('hudson.model.Item.Build', 'anonymous')
    }

    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/01_ssh_configuration.groovy'))
        }
    }
}

pipelineJob('02_lior_backend') {
    // Enable remote builds from anonymous users
    authorization {
        permission('hudson.model.Item.Build', 'anonymous')
    }

    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/02_lior_backend.groovy'))
        }
    }
}

pipelineJob('03_lior_frontend') {
    // Enable remote builds from anonymous users
    authorization {
        permission('hudson.model.Item.Build', 'anonymous')
    }

    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/03_lior_frontend.groovy'))
        }
    }
}

pipelineJob('04_deploy') {
    // Enable remote builds from anonymous users
    authorization {
        permission('hudson.model.Item.Build', 'anonymous')
    }
    
    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/04_deploy.groovy'))
        }
    }
}

pipelineJob('entire_pipeline') {
    // Enable remote builds from anonymous users
    authorization {
        permission('hudson.model.Item.Build', 'anonymous')
    }
  
    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/entire_pipeline.groovy'))
        }
    }
}
