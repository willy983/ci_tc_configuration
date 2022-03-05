package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_CacheFullApiConfigVariationsWithKeepBinary : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Cache (Full API Config Variations / WithKeepBinary)"

    params {
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "WithKeepBinaryCacheConfigVariationsFullApiTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 240
    }
})
