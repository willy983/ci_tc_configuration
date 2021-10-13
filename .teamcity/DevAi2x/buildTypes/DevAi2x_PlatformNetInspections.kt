package DevAi2x.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.BuildFailureOnMetric
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.failOnMetricChange

object DevAi2x_PlatformNetInspections : BuildType({
    templates(DevAi2x_PreBuild, DevAi2x_RunTestSuitesNet, DevAi2x_PostBuild)
    name = "Platform .NET (Inspections)*"

    steps {
        step {
            name = "FxCop static analysis"
            id = "RUNNER_125"
            type = "FxCop"
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            param("fxcop.version", "not_specified")
            param("fxcop.project", """modules\platforms\dotnet\Apache.Ignite.FxCop""")
            param("fxcop.what", "project")
        }
        step {
            name = "ReSharper Inspections"
            id = "RUNNER_126"
            type = "dotnet-tools-inspectcode"
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            param("TargetDotNetFramework_4.0", "true")
            param("dotnet-tools-inspectcode.solution", "modules/platforms/dotnet/Apache.Ignite.sln")
            param("dotnet-tools-inspectcodeCustomSettingsProfile", "modules/platforms/dotnet/Apache.Ignite.sln.TeamCity.DotSettings")
            param("dotnet-tools-inspectcode.project.filter", """
                Apache.Ignite.Core
                Apache.Ignite.Linq
                Apache.Ignite.AspNet
                Apache.Ignite.EntityFramework
            """.trimIndent())
            param("jetbrains.resharper-clt.clt-path", "%teamcity.tool.jetbrains.resharper-clt.bundled%")
        }
        stepsOrder = arrayListOf("RUNNER_264", "RUNNER_287", "RUNNER_32", "RUNNER_33", "RUNNER_43", "RUNNER_54", "RUNNER_71", "RUNNER_125", "RUNNER_126", "RUNNER_266")
    }

    failureConditions {
        executionTimeoutMin = 120
        failOnMetricChange {
            id = "BUILD_EXT_3"
            metric = BuildFailureOnMetric.MetricType.INSPECTION_ERROR_COUNT
            threshold = 0
            units = BuildFailureOnMetric.MetricUnit.DEFAULT_UNIT
            comparison = BuildFailureOnMetric.MetricComparison.MORE
            compareTo = value()
            param("anchorBuild", "lastSuccessful")
        }
        failOnMetricChange {
            id = "BUILD_EXT_4"
            metric = BuildFailureOnMetric.MetricType.INSPECTION_WARN_COUNT
            threshold = 0
            units = BuildFailureOnMetric.MetricUnit.DEFAULT_UNIT
            comparison = BuildFailureOnMetric.MetricComparison.MORE
            compareTo = value()
            param("anchorBuild", "lastSuccessful")
        }
    }
    
    disableSettings("ARTIFACT_DEPENDENCY_103", "RUNNER_32", "RUNNER_54", "RUNNER_71")
})
