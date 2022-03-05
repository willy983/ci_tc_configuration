package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_Queries3lazyTrue : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Queries 3 (lazy=true)"

    params {
        text("system.IGNITE_QUERY_LAZY_DEFAULT", "true", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_MODULES", ":ignite-indexing", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteBinaryCacheQueryLazyTestSuite3", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 240
    }
})
