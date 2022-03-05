package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_CacheRestarts1 : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Cache (Restarts) 1"

    params {
        param("JVM_ARGS", "-Xss512k")
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteCacheRestartTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
