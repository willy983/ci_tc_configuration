package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_CacheFullApiConfigVariations : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Cache (Full API Config Variations / WithKeepBinary)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "WithKeepBinaryCacheConfigVariationsFullApiTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 240
    }
})
