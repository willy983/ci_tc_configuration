package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object CacheTxRecovery : BuildType({
    templates(RunTestSuitesJava)
    name = "Cache (Tx Recovery)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheTxRecoverySelfTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
