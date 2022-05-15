package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_RunCpp : BuildType({
    name = "-> Run :: CPP"
    description = "Run all C++ related suites"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite)

        checkoutMode = CheckoutMode.ON_SERVER
        showDependenciesChanges = true
    }

    dependencies {
        snapshot(TestAltLinIgniteTests2xJdk811_CheckCodeStyle) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Javadoc) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_LicensesHeaders) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PlatformCCMakeWinX64Debug) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PlatformCCMakeWinX64Release) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PlatformCPPCMakeLinux) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PlatformCPPCMakeLinuxClang) {
        }
    }
})
