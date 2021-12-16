package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_LoggingOld : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] Logging"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteLoggingSelfTestSuite")
        param("system.FORK_COUNT_SET_TO_1", "true")
    }

    failureConditions {
        executionTimeoutMin = 20
    }
})
