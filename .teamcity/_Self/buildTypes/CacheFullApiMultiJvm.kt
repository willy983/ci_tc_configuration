package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object CacheFullApiMultiJvm : BuildType({
    templates(RunTestSuitesJava)
    name = "Cache (Full API Multi JVM)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheFullApiMultiJvmSelfTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 240
    }
})
