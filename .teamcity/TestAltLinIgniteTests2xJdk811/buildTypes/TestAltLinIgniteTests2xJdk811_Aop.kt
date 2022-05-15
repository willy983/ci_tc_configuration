package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_Aop : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "AOP"

    params {
        checkbox("IGNITE_LOGGING_OPTS", "-DIGNITE_TEST_PROP_LOG4J_FILE=log4j-tc-test.xml -DIGNITE_QUIET=true", label = "Quite console output",
                  checked = "-DIGNITE_TEST_PROP_LOG4J_FILE=log4j-tc-test.xml -DIGNITE_QUIET=true", unchecked = "-DIGNITE_QUIET=false")
        text("MAVEN_MODULES", ":ignite-aop", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteAopSelfTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 30
    }
})
