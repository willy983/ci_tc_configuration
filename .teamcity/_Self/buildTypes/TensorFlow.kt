package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TensorFlow : BuildType({
    templates(ExcludeTests, RunTestSuitesJava)
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
