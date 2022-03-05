package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_RunCpp : BuildType({
    name = "-> Run :: CPP"
    description = "Run all C++ related suites"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite)

        checkoutMode = CheckoutMode.ON_SERVER
        showDependenciesChanges = true
    }

    dependencies {
        snapshot(TestAltLinIgniteTests24Java8_CheckCodeStyle) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Javadoc) {
        }
        snapshot(TestAltLinIgniteTests24Java8_LicensesHeaders) {
        }
        snapshot(TestAltLinIgniteTests24Java8_PlatformCCMakeWinX64Debug) {
        }
        snapshot(TestAltLinIgniteTests24Java8_PlatformCCMakeWinX64Release) {
        }
        snapshot(TestAltLinIgniteTests24Java8_PlatformCPPCMakeLinux) {
        }
        snapshot(TestAltLinIgniteTests24Java8_PlatformCPPCMakeLinuxClang) {
        }
    }
})
