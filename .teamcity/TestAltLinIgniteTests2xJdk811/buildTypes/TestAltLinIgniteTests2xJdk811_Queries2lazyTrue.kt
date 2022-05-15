package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_Queries2lazyTrue : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Queries 2 (lazy=true)"

    params {
        text("system.IGNITE_QUERY_LAZY_DEFAULT", "true", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_MODULES", ":ignite-indexing", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteBinaryCacheQueryLazyTestSuite2", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 240
    }
})
