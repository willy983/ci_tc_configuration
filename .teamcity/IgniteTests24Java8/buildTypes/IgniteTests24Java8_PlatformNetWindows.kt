package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.exec
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.BuildFailureOnMetric
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.failOnMetricChange

object IgniteTests24Java8_PlatformNetWindows : BuildType({
    templates(IgniteTests24Java8_PreBuild, IgniteTests24Java8_PostBuild)
    name = "Platform .NET (Windows)"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        modules/platforms/dotnet/Apache.Ignite.Core.Tests.DotNetCore/bin/Debug/netcoreapp2.0/*.log => logs.zip
    """.trimIndent()

    params {
        param("env.IGNITE_BASELINE_AUTO_ADJUST_ENABLED", "false")
        text("env.PATH", "%teamcity.tool.maven.DEFAULT%/bin:%env.PATH%", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("env.JAVA_HOME", "%env.JDK_ORA_8%", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        param("env.COMPlus_EnableAlternateStackCheck", "1")
    }

    steps {
        script {
            name = "Build .NET"
            id = "RUNNER_79"
            workingDir = "modules/platforms/dotnet"
            scriptContent = "dotnet build Apache.Ignite.sln"
        }
        exec {
            name = "NUnit: Apache.Ignite.Core.Tests"
            id = "RUNNER_119"
            enabled = false
            workingDir = "modules/platforms/dotnet/Apache.Ignite.Core.Tests/bin/Debug/net461"
            path = "modules/platforms/dotnet/Apache.Ignite.Core.Tests/bin/Debug/net461/nunit/nunit3-console.exe"
            arguments = "Apache.Ignite.Core.Tests.exe --teamcity"
            formatStderrAsError = true
        }
        exec {
            name = "NUnit: Apache.Ignite.AspNet.Tests"
            id = "RUNNER_171"
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            workingDir = "modules/platforms/dotnet/Apache.Ignite.AspNet.Tests/bin/Debug/net461"
            path = "modules/platforms/dotnet/Apache.Ignite.Core.Tests/bin/Debug/net461/nunit/nunit3-console.exe"
            arguments = "Apache.Ignite.AspNet.Tests.dll --teamcity"
            formatStderrAsError = true
        }
        exec {
            name = "NUnit: Apache.Ignite.EntityFramework.Tests"
            id = "RUNNER_148"
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            workingDir = "modules/platforms/dotnet/Apache.Ignite.EntityFramework.Tests/bin/Debug/net461"
            path = "modules/platforms/dotnet/Apache.Ignite.Core.Tests/bin/Debug/net461/nunit/nunit3-console.exe"
            arguments = "Apache.Ignite.EntityFramework.Tests.dll --teamcity"
            formatStderrAsError = true
        }
        stepsOrder = arrayListOf("RUNNER_264", "RUNNER_287", "RUNNER_79", "RUNNER_63", "RUNNER_119", "RUNNER_171", "RUNNER_148", "RUNNER_266")
    }

    failureConditions {
        executionTimeoutMin = 120
        failOnMetricChange {
            id = "BUILD_EXT_16"
            metric = BuildFailureOnMetric.MetricType.INSPECTION_ERROR_COUNT
            threshold = 0
            units = BuildFailureOnMetric.MetricUnit.DEFAULT_UNIT
            comparison = BuildFailureOnMetric.MetricComparison.MORE
            compareTo = value()
        }
        failOnMetricChange {
            id = "BUILD_EXT_17"
            metric = BuildFailureOnMetric.MetricType.INSPECTION_WARN_COUNT
            threshold = 0
            units = BuildFailureOnMetric.MetricUnit.DEFAULT_UNIT
            comparison = BuildFailureOnMetric.MetricComparison.MORE
            compareTo = value()
        }
    }

    requirements {
        exists("env.windir", "RQ_22")
    }
    
    disableSettings("swabra")
})
