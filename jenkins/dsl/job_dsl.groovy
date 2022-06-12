import org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval

pipelineJob('ssh_configuration') {
    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/01_ssh_configuration.groovy'))
        }
    }
}

pipelineJob('lior_backend') {
    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/02_lior_backend.groovy'))
        }
    }
}

pipelineJob('lior_frontend') {
    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/03_lior_frontend.groovy'))
        }
    }
}

pipelineJob('deploy') {
    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/04_deploy.groovy'))
        }
    }
}

pipelineJob('deploy') {
    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/entire_pipeline.groovy'))
        }
    }
}

// https://stackoverflow.com/a/55940005/13630006
// Useless ??
ScriptApproval scriptApproval = ScriptApproval.get()
scriptApproval.pendingScripts.each {
    scriptApproval.approveScript(it.hash)
}