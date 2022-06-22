package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Pds2 : BuildType({
    templates(IgniteTests24Java8_RunTestsJava)
    name = "PDS 2"

    params {
        text("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgnitePdsTestSuite2", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
