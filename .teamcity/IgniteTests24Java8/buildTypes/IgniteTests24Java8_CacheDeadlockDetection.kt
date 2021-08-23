package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_CacheDeadlockDetection : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Cache (Deadlock Detection)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("XMX", "2g")
        param("TEST_SUITE", "TxDeadlockDetectionTestSuite")
        param("XMS", "2g")
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
