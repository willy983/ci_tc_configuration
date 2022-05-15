package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_ScalaVisorConsole : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "Scala (Visor Console)"

    params {
        text("MAVEN_MODULES", ":ignite-visor-console", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "VisorConsoleSelfTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 20
    }
})
