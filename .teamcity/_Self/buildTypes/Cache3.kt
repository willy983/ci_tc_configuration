package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Cache3 : BuildType({
    templates(RunTestSuitesJava)
    name = "Cache 3"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteBinaryObjectsCacheTestSuite3")
        param("XMX", "4g")
    }

    failureConditions {
        executionTimeoutMin = 210
    }
})
