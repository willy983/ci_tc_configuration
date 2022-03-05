package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_BinaryObjectsSimpleMapperCacheFullApi : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Binary Objects (Simple Mapper Cache Full API"

    params {
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteBinarySimpleNameMapperCacheFullApiTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
