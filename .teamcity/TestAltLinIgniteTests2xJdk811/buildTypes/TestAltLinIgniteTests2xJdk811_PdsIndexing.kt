package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_PdsIndexing : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "PDS (Indexing)"

    params {
        text("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_MODULES", ":ignite-indexing", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgnitePdsWithIndexingCoreTestSuite,IgnitePdsWithIndexingTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
