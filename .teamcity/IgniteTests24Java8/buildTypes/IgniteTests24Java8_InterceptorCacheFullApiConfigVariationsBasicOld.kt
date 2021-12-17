package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_InterceptorCacheFullApiConfigVariationsBasicOld : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] Interceptor Cache (Full API Config Variations / Basic)*"

    params {
        param("MAVEN_MODULES", ":ignite-core")
        param("TEST_SUITE", "InterceptorCacheConfigVariationsFullApiTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 240
    }

    requirements {
        doesNotEqual("system.agent.name", "publicagent09_9096", "RQ_24")
    }
})
