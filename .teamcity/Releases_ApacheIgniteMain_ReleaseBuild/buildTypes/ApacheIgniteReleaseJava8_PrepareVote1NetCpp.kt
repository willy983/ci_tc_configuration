package Releases_ApacheIgniteMain_ReleaseBuild.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.MavenBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.PowerShellStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.VisualStudioStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.visualStudio

object ApacheIgniteReleaseJava8_PrepareVote1NetCpp : BuildType({
    name = "[1] Build .Net & C++"

    artifactRules = """
        ignite\modules\platforms\dotnet\Apache.Ignite\bin\x64\Release\** => ignite.dotnet.bin.zip
        ignite\modules\platforms\dotnet\Apache.Ignite\bin\Release\** => ignite.dotnet.bin.zip
        ignite\modules\platforms\dotnet\Apache.Ignite\bin\x86\Release\** => ignite.dotnet.bin.zip!x86
        ignite\modules\platforms\dotnet\Apache.Ignite.Linq\bin\Release\** => ignite.dotnet.bin.zip
        ignite\modules\platforms\dotnet\Apache.Ignite.AspNet\bin\Release\** => ignite.dotnet.bin.zip
        ignite\modules\platforms\dotnet\Apache.Ignite.EntityFramework\bin\Release\** => ignite.dotnet.bin.zip
        ignite\modules\platforms\dotnet\Apache.Ignite.NLog\bin\Release\** => ignite.dotnet.bin.zip
        ignite\modules\platforms\dotnet\Apache.Ignite.Log4net\bin\Release\** => ignite.dotnet.bin.zip
        ignite\modules\platforms\dotnet\nupkg\*.nupkg => .
        ignite\modules\platforms\cpp\odbc\install\*.msi => ignite.odbc.installers.zip
        ignite\modules\platforms\cpp\odbc\install\dummy => ignite.odbc.installers.zip
        ignite\modules\clients\target\dotnetdoc => dotnetdoc.zip
        vote.patch
    """.trimIndent()

    params {
        param("env.OPENSSL_HOME", """C:\OpenSSL-Win64""")
        param("IGNITE_VERSION", "")
        param("RC_NAME", "")
        param("env.JAVA_HOME", "%env.JDK_ORA_8%")
        param("system.JAVA_HOME", "%env.JAVA_HOME%")
        param("env.OPENSSL_HOME_X86", """C:\OpenSSL-Win32""")
    }

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite, "+:. => ./ignite")

        cleanCheckout = true
        excludeDefaultBranchChanges = true
    }

    steps {
        script {
            name = "Add NuGet executable to PATH"
            scriptContent = """
                @echo on
                
                echo ##teamcity[setParameter name='env.PATH' value='%%PATH%%;%teamcity.tool.NuGet.CommandLine.DEFAULT%\tools']
            """.trimIndent()
        }
        script {
            name = "Git setup (Ignore chmod settings)"
            workingDir = "ignite"
            scriptContent = "git config core.filemode false"
        }
        maven {
            name = "Change maven version"
            goals = "versions:set"
            pomLocation = "ignite/pom.xml"
            runnerArgs = "-DnewVersion=%IGNITE_VERSION% -Pall-java,all-scala,all-other -DgenerateBackupPoms=false  -DgroupId=* -DartifactId=* -DoldVersion=* -DprocessDependencies=false"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
        }
        maven {
            name = "Change dotnet & cpp versions"
            goals = "validate"
            pomLocation = "ignite/pom.xml"
            runnerArgs = "-P update-versions -Dnew.ignite.version=%IGNITE_VERSION%"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
        }
        script {
            name = "Git patch (with changed versions) generation"
            workingDir = "ignite"
            scriptContent = "git diff > ../vote.patch"
        }
        powerShell {
            name = "Build Ignite.NET"
            platform = PowerShellStep.Platform.x64
            edition = PowerShellStep.Edition.Desktop
            workingDir = "ignite/modules/platforms/dotnet"
            scriptMode = file {
                path = "ignite/modules/platforms/dotnet/build.ps1"
            }
            noProfile = false
            param("jetbrains_powershell_scriptArguments", "-skipJava -skipNuget")
        }
        visualStudio {
            name = "Build 32-bit ODBC binary"
            path = "ignite/modules/platforms/cpp/project/vs/ignite.sln"
            version = VisualStudioStep.VisualStudioVersion.vs2010
            runPlatform = VisualStudioStep.Platform.x86
            msBuildVersion = VisualStudioStep.MSBuildVersion.V4_0
            msBuildToolsVersion = VisualStudioStep.MSBuildToolsVersion.V4_0
            targets = "odbc:Rebuild"
            configuration = "Release"
            platform = "Win32"
        }
        visualStudio {
            name = "Build 64-bit ODBC binary"
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            path = "ignite/modules/platforms/cpp/project/vs/ignite.sln"
            version = VisualStudioStep.VisualStudioVersion.vs2010
            runPlatform = VisualStudioStep.Platform.x86
            msBuildVersion = VisualStudioStep.MSBuildVersion.V4_0
            msBuildToolsVersion = VisualStudioStep.MSBuildToolsVersion.V4_0
            targets = "odbc:Rebuild"
            configuration = "Release"
            platform = "x64"
        }
        script {
            name = "Build 32-bit ODBC installer"
            workingDir = "ignite/modules/platforms/cpp/odbc/install"
            scriptContent = """
                candle.exe ignite-odbc-x86.wxs || exit 1
                light.exe -ext WixUIExtension ignite-odbc-x86.wixobj || exit 1
            """.trimIndent()
        }
        script {
            name = "Build 64-bit ODBC installer"
            workingDir = "ignite/modules/platforms/cpp/odbc/install"
            scriptContent = """
                candle.exe ignite-odbc-amd64.wxs || exit 1
                light.exe -ext WixUIExtension ignite-odbc-amd64.wixobj || exit 1
            """.trimIndent()
        }
        script {
            name = "Build dotnetdoc"
            workingDir = "ignite/modules/platforms/dotnet/docfx"
            scriptContent = "generate-docs.cmd"
        }
    }
})
