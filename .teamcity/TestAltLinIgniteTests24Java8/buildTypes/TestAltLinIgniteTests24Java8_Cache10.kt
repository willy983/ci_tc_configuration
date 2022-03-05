package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_Cache10 : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Cache 10"

    params {
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteCacheTestSuite10", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
