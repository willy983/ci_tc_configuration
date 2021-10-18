package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.PowerShellStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.VisualStudioStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.visualStudio
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'Releases_NightlyRelease_ApacheIgniteNightlyReleaseBuildNetCpp'
in the project with id = 'Releases_ApacheIgniteNightly', and delete the patch script.
*/
create(RelativeId("Releases_ApacheIgniteNightly"), BuildType({
    id("Releases_NightlyRelease_ApacheIgniteNightlyReleaseBuildNetCpp")
    name = "[APACHE IGNITE NIGHTLY RELEASE] #1 :: Build .Net & C++"

    artifactRules = """
        modules\platforms\dotnet\Apache.Ignite\bin\x64\Release\** => ignite.dotnet.bin.zip
        modules\platforms\dotnet\Apache.Ignite\bin\Release\** => ignite.dotnet.bin.zip
        modules\platforms\dotnet\Apache.Ignite\bin\x86\Release\** => ignite.dotnet.bin.zip!x86
        modules\platforms\dotnet\Apache.Ignite.Linq\bin\Release\** => ignite.dotnet.bin.zip
        modules\platforms\dotnet\Apache.Ignite.AspNet\bin\Release\** => ignite.dotnet.bin.zip
        modules\platforms\dotnet\Apache.Ignite.EntityFramework\bin\Release\** => ignite.dotnet.bin.zip
        modules\platforms\dotnet\Apache.Ignite.NLog\bin\Release\** => ignite.dotnet.bin.zip
        modules\platforms\dotnet\Apache.Ignite.Log4net\bin\Release\** => ignite.dotnet.bin.zip
        modules\platforms\cpp\install\amd64\bin\*.msi => ignite.odbc.installers.zip
        modules\platforms\cpp\install\x86\bin\*.msi => ignite.odbc.installers.zip
        modules\clients\target\dotnetdoc => dotnetdoc.zip
    """.trimIndent()

    params {
        text("env.OPENSSL_HOME", """C:\OpenSSL-Win64""", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        param("IGNITE_VERSION", "%dep.Releases_NightlyRelease_ApacheIgniteNightlyReleasePrepare.IGNITE_VERSION%")
        text("env.JAVA_HOME", "%env.JDK_ORA_8%", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        param("env.OPENSSL_HOME_X86", """C:\OpenSSL-Win32""")
    }

    vcs {
        root(RelativeId("GitHubApacheIgnite"))

        cleanCheckout = true
    }

    steps {
        nuGetInstaller {
            name = "Get NuGet dependencies"
            toolPath = "%teamcity.tool.NuGet.CommandLine.4.7.0%"
            projects = "modules/platforms/dotnet/Apache.Ignite.sln"
        }
        powerShell {
            name = "Build .NET"
            platform = PowerShellStep.Platform.x64
            edition = PowerShellStep.Edition.Desktop
            workingDir = "modules/platforms/dotnet"
            scriptMode = file {
                path = "modules/platforms/dotnet/build.ps1"
            }
            noProfile = false
            param("jetbrains_powershell_scriptArguments", """
                -skipJava
                -skipNuget
            """.trimIndent())
        }
        visualStudio {
            name = "Build 32-bit ODBC"
            enabled = false
            path = "modules/platforms/cpp/project/vs/ignite.sln"
            version = VisualStudioStep.VisualStudioVersion.vs2017
            runPlatform = VisualStudioStep.Platform.x86
            msBuildVersion = VisualStudioStep.MSBuildVersion.V15_0
            msBuildToolsVersion = VisualStudioStep.MSBuildToolsVersion.V15_0
            targets = "odbc:Rebuild"
            configuration = "Release"
            platform = "Win32"
        }
        visualStudio {
            name = "Build 64-bit ODBC"
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            path = "modules/platforms/cpp/project/vs/ignite.sln"
            version = VisualStudioStep.VisualStudioVersion.vs2017
            runPlatform = VisualStudioStep.Platform.x86
            msBuildVersion = VisualStudioStep.MSBuildVersion.V15_0
            msBuildToolsVersion = VisualStudioStep.MSBuildToolsVersion.V15_0
            targets = "odbc:Rebuild"
            configuration = "Release"
            platform = "x64"
        }
        script {
            name = "Build 32-bit ODBC installer"
            workingDir = "modules/platforms/cpp/odbc/install"
            scriptContent = """
                candle.exe ignite-odbc-x86.wxs || exit 1
                light.exe -ext WixUIExtension ignite-odbc-x86.wixobj || exit 1
            """.trimIndent()
        }
        script {
            name = "Build 64-bit ODBC installer"
            workingDir = "modules/platforms/cpp/odbc/install"
            scriptContent = """
                candle.exe ignite-odbc-amd64.wxs || exit 1
                light.exe -ext WixUIExtension ignite-odbc-amd64.wixobj || exit 1
            """.trimIndent()
        }
        script {
            name = "Build .NET documentation"
            workingDir = "modules/platforms/dotnet/docfx"
            scriptContent = "generate-docs.cmd"
        }
    }

    dependencies {
        dependency(RelativeId("Releases_NightlyRelease_ApacheIgniteNightlyReleasePrepare")) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                cleanDestination = true
                artifactRules = "apache-ignite-%IGNITE_VERSION%-src.zip!** => ."
            }
        }
    }
}))

