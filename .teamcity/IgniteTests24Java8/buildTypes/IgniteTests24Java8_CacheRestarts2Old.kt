package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_CacheRestarts2Old : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] Cache (Restarts) 2"

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
