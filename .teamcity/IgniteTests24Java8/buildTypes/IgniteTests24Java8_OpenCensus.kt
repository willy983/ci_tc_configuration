package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_OpenCensus : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Open Census"

    params {
        param("MAVEN_MODULES", ":ignite-opencensus")
        param("TEST_SUITE", "IgniteOpenCensusSuite")
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
