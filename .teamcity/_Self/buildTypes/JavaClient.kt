package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object JavaClient : BuildType({
    templates(RunTestSuitesJava)
    name = "Java Client"

    params {
        param("JVM_ARGS", "-Xmx8g")
        param("MAVEN_MODULES", ":ignite-clients")
        param("TEST_SUITE", "IgniteClientTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 50
    }
})
