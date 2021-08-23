package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_Hadoop : BuildType({
    templates(IgniteTests24Java8_ExcludeTests, IgniteTests24Java8_RunTestSuitesJava)
    name = "Hadoop"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
    """.trimIndent()

    params {
        text("env.LD_LIBRARY_PATH", "/usr/local/lib:%system.teamcity.build.tempDir%/__hadoop/hadoop-%system.hadoop.version%/lib/native", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("system.hadoop.version", "2.5.2", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_MODULES", ":ignite-hadoop", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("XMX", "5g", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        param("SKIP_BUILD_CONDITION", "! -f modules/hadoop/pom.xml")
        text("TEST_SUITE", "IgniteHadoopTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
