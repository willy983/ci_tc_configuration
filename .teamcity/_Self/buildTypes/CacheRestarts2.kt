package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object CacheRestarts2 : BuildType({
    templates(RunTestSuitesJava)
    name = "Cache (Restarts) 2"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("XMX", "4g")
        param("JVM_ARGS", "-Xss512k")
        param("TEST_SUITE", "IgniteCacheRestartTestSuite2")
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
