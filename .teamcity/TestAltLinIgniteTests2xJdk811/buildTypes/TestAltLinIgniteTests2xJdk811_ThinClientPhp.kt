package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_ThinClientPhp : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_ThirdpartyCheckout, TestAltLinIgniteTests2xJdk811_PreBuild, _Self.buildTypes.ThinClientStartIgnite, _Self.buildTypes.RunPhpTests, _Self.buildTypes.ThinClientStopIgnite, TestAltLinIgniteTests2xJdk811_PostBuild)
    name = "Thin client: PHP"

    artifactRules = "work/log/** => logs.zip"

    params {
        text("REPOSITORY_LIST", "%VCS_ROOT_PHP%", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnitePhpThinClient, "+:. => %VCS_ROOT_PHP%")

        checkoutMode = CheckoutMode.ON_AGENT
    }

    failureConditions {
        executionTimeoutMin = 30
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_35")
    }
})
