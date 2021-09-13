package DevAi2x.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object DevAi2x_Cache8 : BuildType({
    templates(DevAi2x_RunTestSuitesJava)
    name = "Cache 8"

    params {
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteCacheTestSuite8", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("XMX", "4g", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 210
    }
})
