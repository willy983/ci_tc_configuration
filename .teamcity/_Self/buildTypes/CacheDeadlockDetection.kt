package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object CacheDeadlockDetection : BuildType({
    templates(RunTestSuitesJava)
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
