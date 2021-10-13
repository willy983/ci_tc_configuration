package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_CacheFullApiBasicConfigVariations : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Cache (Full API Config Variations / Basic)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "IgniteCacheBasicConfigVariationsFullApiTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 240
    }
})
