package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object ThinClientPhp : BuildType({
    templates(RelativeId("ThirdpartyCheckout"), RelativeId("PreBuild"), AbsoluteId("ThinClientStartIgnite"), AbsoluteId("RunPhpTests"), AbsoluteId("ThinClientStopIgnite"), RelativeId("PostBuild"))
    name = "Thin client: PHP"

    artifactRules = "work/log/** => logs.zip"

    params {
        text("REPOSITORY_LIST", "%VCS_ROOT_PHP%", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(AbsoluteId("GitHubApacheIgnitePhpThinClient"), "+:. => %VCS_ROOT_PHP%")

        checkoutMode = CheckoutMode.ON_AGENT
    }

    failureConditions {
        executionTimeoutMin = 30
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_35")
    }
})
