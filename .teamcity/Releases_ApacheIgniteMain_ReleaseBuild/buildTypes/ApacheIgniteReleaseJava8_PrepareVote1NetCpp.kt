package Releases_ApacheIgniteMain_ReleaseBuild.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.MavenBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.PowerShellStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

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
        ignite\modules\platforms\cpp\install\amd64\bin\*.msi => ignite.odbc.installers.zip
        ignite\modules\platforms\cpp\install\x86\bin\*.msi => ignite.odbc.installers.zip
        ignite\modules\clients\target\dotnetdoc => dotnetdoc.zip
        vote.patch
    """.trimIndent()

    params {
        param("env.OPENSSL_HOME", """C:\openssl\1.1.0l\x86_64""")
        param("env.OPENSSL_HOME_x86", """C:\openssl\1.1.0l\x86""")
        param("IGNITE_VERSION", "")
        param("RC_NAME", "")
        param("env.JAVA_HOME", "%env.JDK_ORA_8%")
        param("system.JAVA_HOME", "%env.JAVA_HOME%")
    }

    vcs {
        root(RelativeId("GitHubApacheIgnite"), "+:. => ./ignite")

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
        script {
            name = "Build 32-bit ODBC installer"
            workingDir = "ignite/modules/platforms/cpp"
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
            workingDir = "ignite/modules/platforms/cpp"
            scriptContent = """
                set OPENSSL_ROOT_DIR=%env.OPENSSL_HOME%
                mkdir cmake-build-release-64
                cd cmake-build-release-64
                
                cmake -DWITH_CORE=OFF -DWITH_ODBC=ON -DWITH_ODBC_MSI=ON -DCMAKE_BUILD_TYPE=Release -DCMAKE_GENERATOR_PLATFORM=x64 -DCMAKE_INSTALL_PREFIX=..\install\amd64 ..
                cmake --build . --target install --config Release
            """.trimIndent()
        }
        script {
            name = "Build dotnetdoc"
            workingDir = "ignite/modules/platforms/dotnet/docfx"
            scriptContent = "generate-docs.cmd"
        }
    }
})

