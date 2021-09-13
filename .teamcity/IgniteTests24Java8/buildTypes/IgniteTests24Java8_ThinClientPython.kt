package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_ThinClientPython : BuildType({
    templates(IgniteTests24Java8_ThirdpartyCheckout, IgniteTests24Java8_PreBuild, _Self.buildTypes.ThinClientStartIgnite, _Self.buildTypes.RunPythonTestsBasic, _Self.buildTypes.ThinClientStopIgnite, _Self.buildTypes.RunPythonTestsSsl, IgniteTests24Java8_PostBuild)
    name = "[Old] Thin client: Python"

    artifactRules = "work/log/** => logs.zip"

    params {
        text("REPOSITORY_LIST", "%VCS_ROOT_PYTHON%", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnitePythonThinClient, "+:. => %VCS_ROOT_PYTHON%")

        checkoutMode = CheckoutMode.ON_AGENT
    }

    failureConditions {
        executionTimeoutMin = 30
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_35")
    }
})
