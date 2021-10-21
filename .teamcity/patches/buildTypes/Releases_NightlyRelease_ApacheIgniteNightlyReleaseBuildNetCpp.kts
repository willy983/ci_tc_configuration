package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.PowerShellStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
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
        text("env.OPENSSL_HOME", """C:\openssl\1.1.0l\x86_64""", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        param("env.OPENSSL_HOME_x86", """C:\openssl\1.1.0l\x86""")
        param("IGNITE_VERSION", "%dep.Releases_NightlyRelease_ApacheIgniteNightlyReleasePrepare.IGNITE_VERSION%")
        text("env.JAVA_HOME", "%env.JDK_ORA_8%", display = ParameterDisplay.HIDDEN, allowEmpty = true)
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
            enabled = false
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
        script {
            name = "Build 32-bit ODBC installer"
            workingDir = "modules/platforms/cpp"
            scriptContent = """
                set OPENSSL_ROOT_DIR=%env.OPENSSL_HOME_x86%
                mkdir cmake-build-release-32
                cd cmake-build-release-32
                
                cmake -DWITH_CORE=OFF -DWITH_ODBC=ON -DWITH_ODBC_MSI=ON -DCMAKE_BUILD_TYPE=Release -DCMAKE_GENERATOR_PLATFORM=Win32 -DCMAKE_INSTALL_PREFIX=..\install\x86 ..
                cmake --build . --target install --config Release
            """.trimIndent()
        }
        script {
            name = "Build 64-bit ODBC installer"
            workingDir = "modules/platforms/cpp"
            scriptContent = """
                set OPENSSL_ROOT_DIR=%env.OPENSSL_HOME%
                mkdir cmake-build-release-64
                cd cmake-build-release-64
                
                cmake -DWITH_CORE=OFF -DWITH_ODBC=ON -DWITH_ODBC_MSI=ON -DCMAKE_BUILD_TYPE=Release -DCMAKE_GENERATOR_PLATFORM=x64 -DCMAKE_INSTALL_PREFIX=..\install\amd64 ..
                cmake --build . --target install --config Release
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

