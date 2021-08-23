package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object Kubernetes : BuildType({
    templates(RunTestSuitesJava)
    name = "Kubernetes"

    params {
        param("MAVEN_MODULES", ":ignite-kubernetes")
        param("TEST_SUITE", "IgniteKubernetesTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 10
    }
})
