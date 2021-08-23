package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object CalciteSql : BuildType({
    templates(RunTestSuitesJava)
    name = "Calcite SQL"
    description = "Run Calcite-based SQL engine tests"

    params {
        param("MAVEN_MODULES", ":ignite-calcite")
        param("TEST_SUITE", "IgniteCalciteTestSuite")
    }
})
