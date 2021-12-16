package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_DevUtilsOld : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] Dev Utils"

    params {
        text("MAVEN_MODULES", ":ignite-dev-utils", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "DevUtilsTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
