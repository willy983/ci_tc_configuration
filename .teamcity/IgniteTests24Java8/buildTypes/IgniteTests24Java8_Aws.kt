package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Aws : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "AWS"

    params {
        param("MAVEN_MODULES", ":ignite-aws")
        param("TEST_SUITE", "IgniteS3TestSuite,IgniteElbTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
