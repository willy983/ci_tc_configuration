package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_InterceptorCacheFullApiConfigVariationsBasic : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Interceptor Cache (Full API Config Variations / Basic)"

    params {
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "InterceptorCacheConfigVariationsFullApiTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 240
    }
})
