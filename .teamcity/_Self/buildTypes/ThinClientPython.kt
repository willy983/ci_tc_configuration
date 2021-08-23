package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object ThinClientPython : BuildType({
    templates(RelativeId("ThirdpartyCheckout"), RelativeId("PreBuild"), AbsoluteId("ThinClientStartIgnite"), AbsoluteId("RunPythonTestsBasic"), AbsoluteId("ThinClientStopIgnite"), AbsoluteId("RunPythonTestsSsl"), RelativeId("PostBuild"))
    name = "[Old] Thin client: Python"

    artifactRules = "work/log/** => logs.zip"

    params {
        text("REPOSITORY_LIST", "%VCS_ROOT_PYTHON%", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(AbsoluteId("GitHubApacheIgnitePythonThinClient"), "+:. => %VCS_ROOT_PYTHON%")

        checkoutMode = CheckoutMode.ON_AGENT
    }

    failureConditions {
        executionTimeoutMin = 30
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_35")
    }
})
