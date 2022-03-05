package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_RunAllNet : BuildType({
    name = "-> Run :: .NET"
    description = "Run all .NET related suites"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite)

        checkoutMode = CheckoutMode.ON_SERVER
        showDependenciesChanges = true
    }

    dependencies {
        snapshot(TestAltLinIgniteTests24Java8_LicensesHeaders) {
        }
        snapshot(TestAltLinIgniteTests24Java8_PlatformNetCoreLinux) {
        }
        snapshot(TestAltLinIgniteTests24Java8_PlatformNetWindows) {
        }
    }
})
