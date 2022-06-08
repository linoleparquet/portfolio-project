import org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval

pipelineJob('lior_backend') {
    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/lior_backend.groovy'))
        }
    }
}

pipelineJob('lior_frontend') {
    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/lior_frontend.groovy'))
        }
    }
}

pipelineJob('deploy') {
    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/deploy.groovy'))
        }
    }
}

pipelineJob('ssh_configuration') {
    definition {
        cps {
            script(readFileFromWorkspace('jenkins/dsl/ssh_configuration.groovy'))
        }
    }
}

// https://stackoverflow.com/a/55940005/13630006
// Useless ??
ScriptApproval scriptApproval = ScriptApproval.get()
scriptApproval.pendingScripts.each {
    scriptApproval.approveScript(it.hash)
}