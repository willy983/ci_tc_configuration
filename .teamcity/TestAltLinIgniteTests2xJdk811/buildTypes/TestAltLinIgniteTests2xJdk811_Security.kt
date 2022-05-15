package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_Security : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Security"

    params {
        text("MAVEN_OPTS", "-DforkMode=always", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("env.IGNITE_TEST_FEATURES_ENABLED", "true", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "SecurityTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
