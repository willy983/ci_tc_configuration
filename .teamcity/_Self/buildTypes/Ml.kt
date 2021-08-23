package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Ml : BuildType({
    templates(RunTestSuitesJava)
    name = "ML"

    params {
        param("MAVEN_MODULES", ":ignite-ml")
        param("TEST_SUITE", "IgniteMLTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 150
    }
})
