package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_TensorFlow : BuildType({
    templates(IgniteTests24Java8_ExcludeTests, IgniteTests24Java8_RunTestSuitesJava)
    name = "TensorFlow"

    params {
        text("MAVEN_MODULES", ":ignite-tensorflow", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("SKIP_BUILD_CONDITION", "! -d tensorflow", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "TensorFlowTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 150
    }
})
