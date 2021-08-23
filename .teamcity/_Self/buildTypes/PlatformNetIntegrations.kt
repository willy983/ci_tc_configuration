package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object PlatformNetIntegrations : BuildType({
    templates(PreBuild, RunTestSuitesNet, PostBuild)
    name = "Platform .NET (Integrations)"

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
