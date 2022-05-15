package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_QueriesBinaryObjectsSimpleMapper : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Queries (Binary Objects Simple Mapper)"

    params {
        text("MAVEN_MODULES", ":ignite-indexing", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteBinarySimpleNameMapperCacheQueryTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
