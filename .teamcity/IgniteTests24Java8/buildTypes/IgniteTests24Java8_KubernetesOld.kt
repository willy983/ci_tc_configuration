package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_KubernetesOld : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] Kubernetes"

    params {
        param("MAVEN_MODULES", ":ignite-kubernetes")
        param("TEST_SUITE", "IgniteKubernetesTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 10
    }
})
