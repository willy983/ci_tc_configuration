package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_RunAllNet : BuildType({
    name = "-> Run :: .NET"
    description = "Run all .NET related suites"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite)

        checkoutMode = CheckoutMode.ON_SERVER
        showDependenciesChanges = true
    }

    dependencies {
        snapshot(TestAltLinIgniteTests2xJdk811_LicensesHeaders) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PlatformNetCoreLinux) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PlatformNetWindows) {
        }
    }
})
