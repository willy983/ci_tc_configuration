package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_PlatformNet : BuildType({
    templates(IgniteTests24Java8_PreBuild, IgniteTests24Java8_RunTestSuitesNet, IgniteTests24Java8_PostBuild)
    name = "~ [DEPRECATED] Platform .NET"
    paused = true

    params {
        param("TEST_CATEGORIES_EXCLUDE_LIST", """
            LONG_TEST
            EXAMPLES_TEST
        """.trimIndent())
        param("env.IGNITE_BASELINE_AUTO_ADJUST_ENABLED", "false")
        param("ASSEMBLY_FILES_INCLUDE_LIST", """modules\platforms\dotnet\Apache.Ignite.Core.Tests\bin\%TARGET_RELEASE%\Apache.Ignite.Core.Tests.exe""")
    }

    failureConditions {
        executionTimeoutMin = 120
    }

    requirements {
        matches("teamcity.agent.name", "(publicagent01_0(1|3|4)_9090|publicagent02_02_9090)", "RQ_64")
    }
    
    disableSettings("RQ_64")
})
