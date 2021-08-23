package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object RunAllNet : BuildType({
    name = "-> Run :: .NET"
    description = "Run all .NET related suites"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(AbsoluteId("GitHubApacheIgnite"))

        checkoutMode = CheckoutMode.ON_SERVER
        showDependenciesChanges = true
    }

    dependencies {
        snapshot(LicensesHeaders) {
        }
        snapshot(PlatformNet) {
        }
        snapshot(PlatformNetCore30Linux) {
        }
        snapshot(PlatformNetCoreLinux) {
        }
        snapshot(PlatformNetIntegrations) {
        }
        snapshot(PlatformNetLongRunning) {
        }
        snapshot(PlatformNetNuGet) {
        }
    }
})
