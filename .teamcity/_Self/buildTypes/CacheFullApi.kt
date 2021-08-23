package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object CacheFullApi : BuildType({
    templates(RunTestSuitesJava)
    name = "Cache (Full API)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheFullApiSelfTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
