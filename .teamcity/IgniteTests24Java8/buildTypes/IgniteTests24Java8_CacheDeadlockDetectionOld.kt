package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_CacheDeadlockDetectionOld : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] Cache (Deadlock Detection)"

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
