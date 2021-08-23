package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object DevUtils : BuildType({
    templates(RunTestSuitesJava)
    name = "Dev Utils"

    params {
        text("MAVEN_MODULES", ":ignite-dev-utils", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "DevUtilsTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
