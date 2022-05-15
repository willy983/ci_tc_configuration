package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_BinaryObjectsSimpleMapperBasic : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Binary Objects (Simple Mapper Basic)"

    params {
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteBinarySimpleNameMapperBasicTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("env.IGNITE_TEST_FEATURES_ENABLED", "true", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
