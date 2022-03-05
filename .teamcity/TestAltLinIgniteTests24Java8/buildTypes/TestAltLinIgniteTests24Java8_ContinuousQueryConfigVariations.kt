package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_ContinuousQueryConfigVariations : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Continuous Query (Config Variations)"

    params {
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteContinuousQueryConfigVariationsSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        param("XMX", "4g")
    }

    failureConditions {
        executionTimeoutMin = 240
    }
})
