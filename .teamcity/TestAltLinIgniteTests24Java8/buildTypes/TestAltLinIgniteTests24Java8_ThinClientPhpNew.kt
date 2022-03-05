package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_ThinClientPhpNew : BuildType({
    templates(TestAltLinIgniteTests24Java8_ThirdpartyCheckout, TestAltLinIgniteTests24Java8_PreBuildNew, _Self.buildTypes.ThinClientStartIgnite, _Self.buildTypes.RunPhpTests, _Self.buildTypes.ThinClientStopIgnite, TestAltLinIgniteTests24Java8_PostBuild)
    name = "Thin client: PHP [NEW]"

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
})
