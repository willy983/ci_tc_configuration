package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object CacheRestarts1 : BuildType({
    templates(RunTestSuitesJava)
    name = "Cache (Restarts) 1"

    params {
        param("JVM_ARGS", "-Xss512k")
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheRestartTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
