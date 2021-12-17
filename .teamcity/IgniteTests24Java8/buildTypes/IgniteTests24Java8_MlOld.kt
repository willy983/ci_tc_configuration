package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_MlOld : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] ML"

    params {
        param("MAVEN_MODULES", ":ignite-ml")
        param("TEST_SUITE", "IgniteMLTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 150
    }
})
