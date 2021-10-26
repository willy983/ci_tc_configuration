package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object IgniteTests24Java8_PlatformNetIntegrations : BuildType({
    templates(IgniteTests24Java8_PreBuild, IgniteTests24Java8_RunTestSuitesNet, IgniteTests24Java8_PostBuild)
    name = "~[DEPRECATED] Platform .NET (Integrations)"
    paused = true

    params {
        param("ASSEMBLY_FILES_INCLUDE_LIST", """
            modules\platforms\dotnet\Apache.Ignite.AspNet.Tests\bin\%TARGET_RELEASE%\Apache.Ignite.AspNet.Tests.dll
            modules\platforms\dotnet\Apache.Ignite.EntityFramework.Tests\bin\%TARGET_RELEASE%\Apache.Ignite.EntityFramework.Tests.dll
        """.trimIndent())
    }

    failureConditions {
        executionTimeoutMin = 120
    }
})
