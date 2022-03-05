package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_JdbcDriver : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "JDBC Driver"

    params {
        text("MAVEN_MODULES", ":ignite-clients", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteJdbcDriverTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("XMX", "4g", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})
