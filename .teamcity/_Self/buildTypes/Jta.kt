package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Jta : BuildType({
    templates(RunTestSuitesJava)
    name = "JTA"

    params {
        param("MAVEN_MODULES", ":ignite-jta")
        param("TEST_SUITE", "IgniteJtaTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 20
    }
})
