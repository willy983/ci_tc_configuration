package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Aws : BuildType({
    templates(RunTestSuitesJava)
    name = "AWS"

    params {
        param("MAVEN_MODULES", ":ignite-aws")
        param("TEST_SUITE", "IgniteS3TestSuite,IgniteElbTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
