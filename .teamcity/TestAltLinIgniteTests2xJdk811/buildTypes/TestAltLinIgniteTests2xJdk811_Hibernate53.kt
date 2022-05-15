package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_Hibernate53 : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Hibernate 5.3"

    params {
        text("MAVEN_MODULES", ":ignite-hibernate_5.3", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteHibernate53TestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 20
    }
})
