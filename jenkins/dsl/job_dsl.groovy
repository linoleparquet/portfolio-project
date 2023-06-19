// Setup the environment SSH-wise:
// Configure the jenkins container to send SSH command to the kubernetes cluster machines
pipelineJob('01_ssh_configuration') {
    authorization {
        // Enable remote builds from anonymous users
        permission('hudson.model.Item.Build', 'anonymous')
    }

    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/01_ssh_configuration.groovy'))
        }
    }
}

// Test, build, and push the backend image to the local registry
pipelineJob('02_lior_backend') {
    authorization {
        // Enable remote builds from anonymous users
        permission('hudson.model.Item.Build', 'anonymous')
    }

    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/02_lior_backend.groovy'))
        }
    }
}

// Test, build, and push the frontend image to the local registry
pipelineJob('03_lior_frontend') {
    authorization {
        // Enable remote builds from anonymous users
        permission('hudson.model.Item.Build', 'anonymous')
    }

    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/03_lior_frontend.groovy'))
        }
    }
}

// Deploy K3s on the Kubernetes cluster machines.
// Then, deploy ArgoCD on top of it.
// Then ArgoCD pull the lior helm chart, and deploy it. 
// This is done using an Ansible playbook.
pipelineJob('04_deploy') {
    authorization {
        // Enable remote builds from anonymous users
        permission('hudson.model.Item.Build', 'anonymous')
    }
    
    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/04_deploy.groovy'))
        }
    }
}

// Wrap up the previous pipelines 
// This is the only pipeline you're supposed to run 
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
