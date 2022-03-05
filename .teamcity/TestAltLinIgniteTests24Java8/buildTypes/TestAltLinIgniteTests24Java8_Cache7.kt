package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_Cache7 : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Cache 7"

    params {
        text("MAVEN_MODULES", ":ignite-core,:ignite-indexing", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteCacheTestSuite7,IgniteCacheWithIndexingAndPersistenceTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
