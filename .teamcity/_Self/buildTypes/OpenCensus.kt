package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object OpenCensus : BuildType({
    templates(RunTestSuitesJava)
    name = "Open Census"

    params {
        param("MAVEN_MODULES", ":ignite-opencensus")
        param("TEST_SUITE", "IgniteOpenCensusSuite")
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
