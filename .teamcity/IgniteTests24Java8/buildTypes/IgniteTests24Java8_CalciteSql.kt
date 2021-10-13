package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_CalciteSql : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJava)
    name = "Calcite SQL"
    description = "Run Calcite-based SQL engine tests"

    params {
        param("MAVEN_MODULES", ":ignite-calcite")
        param("TEST_SUITE", "IgniteCalciteTestSuite")
    }
})
