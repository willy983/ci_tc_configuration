package DevAi2x.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object DevAi2x_Queries2lazyTrue : BuildType({
    templates(DevAi2x_RunTestSuitesJava)
    name = "Queries 2 (lazy=true)"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
    """.trimIndent()

    params {
        param("system.IGNITE_QUERY_LAZY_DEFAULT", "true")
        text("MAVEN_MODULES", ":ignite-indexing", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteBinaryCacheQueryTestSuite2 ", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
