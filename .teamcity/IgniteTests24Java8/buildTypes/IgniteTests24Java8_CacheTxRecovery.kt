package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_CacheTxRecovery : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Cache (Tx Recovery)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheTxRecoverySelfTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
