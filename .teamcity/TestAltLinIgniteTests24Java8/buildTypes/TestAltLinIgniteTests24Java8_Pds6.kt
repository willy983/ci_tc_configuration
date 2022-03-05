package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_Pds6 : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "PDS 6"

    params {
        text("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgnitePdsTestSuite6", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
