package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_CacheFullApi : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Cache (Full API)"

    params {
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteCacheFullApiSelfTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
