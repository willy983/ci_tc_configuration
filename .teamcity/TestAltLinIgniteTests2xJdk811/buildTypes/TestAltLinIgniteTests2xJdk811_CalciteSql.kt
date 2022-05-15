package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_CalciteSql : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Calcite SQL"

    params {
        text("MAVEN_MODULES", ":ignite-calcite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteCalciteTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
