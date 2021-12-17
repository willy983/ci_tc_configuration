package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_ThinClientPhpNew : BuildType({
    templates(AbsoluteId("IgniteTests24Java8_ThirdpartyCheckout"), AbsoluteId("IgniteTests24Java8_PreBuildNew"), AbsoluteId("ThinClientStartIgnite"), AbsoluteId("RunPhpTests"), AbsoluteId("ThinClientStopIgnite"), AbsoluteId("IgniteTests24Java8_PostBuild"))
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
