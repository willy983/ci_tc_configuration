package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_CacheFullApiMultiJvm : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Cache (Full API Multi JVM)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheFullApiMultiJvmSelfTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 240
    }
})
