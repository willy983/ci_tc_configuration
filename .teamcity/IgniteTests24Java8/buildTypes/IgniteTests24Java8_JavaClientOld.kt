package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_JavaClientOld : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] Java Client"

    params {
        param("JVM_ARGS", "-Xmx8g")
        param("MAVEN_MODULES", ":ignite-clients")
        param("TEST_SUITE", "IgniteClientTestSuite,GridRestSuite")
    }

    failureConditions {
        executionTimeoutMin = 50
    }
})
