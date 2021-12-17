package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_PdsIndexingOld : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld)
    name = "~[DEPRECATED] PDS (Indexing)"

    artifactRules = """
        work/log => logs.zip
        work/archive_db => db.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
        ./dev-tools/IGNITE-*-*.patch => patch
    """.trimIndent()

    params {
        param("MAVEN_MODULES", ":ignite-indexing")
        param("env.IGNITE_WORK_DIR_ignore", "/data/teamcity/tmpfs/work")
        param("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000")
        param("TEST_SUITE", "IgnitePdsWithIndexingCoreTestSuite,IgnitePdsWithIndexingTestSuite")
    }

    failureConditions {
        executionTimeoutMin = 180
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_47")
    }
})
