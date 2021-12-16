package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Streamers : BuildType({
    templates(IgniteTests24Java8_RunTestsJava)
    name = "Streamers"

    params {
        text("TEST_SUITE", "IgniteStreamSelfTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
