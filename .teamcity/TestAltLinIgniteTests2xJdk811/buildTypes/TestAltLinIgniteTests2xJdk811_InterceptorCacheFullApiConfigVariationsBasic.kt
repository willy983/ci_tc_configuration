package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_InterceptorCacheFullApiConfigVariationsBasic : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Interceptor Cache (Full API Config Variations / Basic)"

    params {
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "InterceptorCacheConfigVariationsFullApiTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 240
    }
})
