package Releases_ApacheIgniteNightly.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.nuGetPublish
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.powerShell

object Releases_NightlyRelease_ApacheIgniteNightlyReleaseAssembleNugetPackages : BuildType({
    name = "[APACHE IGNITE NIGHTLY RELEASE] #3 :: Assemble Nuget Packages"

    artifactRules = "src/modules/platforms/dotnet/nupkg/*.nupkg => apache-ignite-%IGNITE_VERSION%-nuget-staging.zip"

    params {
        param("IGNITE_VERSION", "${Releases_NightlyRelease_ApacheIgniteNightlyReleasePrepare.depParamRefs["IGNITE_VERSION"]}")
    }

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite)

        checkoutMode = CheckoutMode.ON_SERVER
        cleanCheckout = true
    }

    steps {
        nuGetInstaller {
            name = "Get NuGet dependencies"
            toolPath = "%teamcity.tool.NuGet.CommandLine.DEFAULT%"
            projects = """src\modules\platforms\dotnet\Apache.Ignite.sln"""
        }
        powerShell {
            name = "Pack NuGet"
            workingDir = """src\modules\platforms\dotnet\"""
            scriptMode = file {
                path = """src\modules\platforms\dotnet\build.ps1"""
            }
            param("jetbrains_powershell_scriptArguments", """-skipJava -skipDotNet -jarDirs ..\bin\libs -asmDirs ..\..\..\..\bin\platforms\dotnet\bin""")
        }
        nuGetPublish {
            name = "Publish to MyGet"
            toolPath = "%teamcity.tool.NuGet.CommandLine.4.7.0%"
            packages = "**/Apache*.nupkg"
            serverUrl = "https://www.myget.org/F/apache-ignite-nightly/api/v2/package"
            apiKey = "credentialsJSON:90ad346a-acca-46c4-92b5-03a0b55c4859"
        }
    }

    dependencies {
        dependency(Releases_NightlyRelease_ApacheIgniteNightlyReleaseAssembleBinaries) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                cleanDestination = true
                artifactRules = "apache-ignite-%IGNITE_VERSION%-bin.zip!apache-ignite-%IGNITE_VERSION%-bin/** => bin"
            }
        }
        dependency(Releases_NightlyRelease_ApacheIgniteNightlyReleasePrepare) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                artifactRules = "apache-ignite-%IGNITE_VERSION%-src.zip!** => src"
            }
        }
    }
})
