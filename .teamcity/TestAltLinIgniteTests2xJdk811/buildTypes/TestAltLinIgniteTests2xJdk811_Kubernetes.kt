package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_Kubernetes : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Kubernetes"

    params {
        text("MAVEN_MODULES", ":ignite-kubernetes", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteKubernetesTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 20
    }
})
