package DevAi2x.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object DevAi2x_Pds4 : BuildType({
    templates(DevAi2x_RunTestSuitesJava)
    name = "PDS 4"

    params {
        text("MAVEN_MODULES", ":ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        param("env.IGNITE_WORK_DIR_ignore", "/data/teamcity/tmpfs/work")
        text("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "org.apache.ignite.testsuites.IgnitePdsTestSuite4", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 90
    }
})
