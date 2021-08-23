package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object CacheFullApiConfigVariations : BuildType({
    templates(RunTestSuitesJava)
    name = "Cache (Full API Config Variations / WithKeepBinary)"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "WithKeepBinaryCacheConfigVariationsFullApiTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 240
    }
})
