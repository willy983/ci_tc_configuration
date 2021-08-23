package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object RunCpp : BuildType({
    name = "-> Run :: CPP"
    description = "Run all C++ related suites"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(AbsoluteId("GitHubApacheIgnite"))

        checkoutMode = CheckoutMode.ON_SERVER
        showDependenciesChanges = true
    }

    dependencies {
        snapshot(CheckCodeStyle) {
        }
        snapshot(Javadoc) {
        }
        snapshot(LicensesHeaders) {
        }
        snapshot(PlatformCCMakeWinX64Debug) {
        }
        snapshot(PlatformCPPCMakeLinux) {
        }
        snapshot(PlatformCPPCMakeLinuxClang) {
        }
        snapshot(PlatformCWinX64Debug) {
        }
        snapshot(PlatformCWinX64Release) {
        }
        snapshot(WiPPlatformCCMakeWinX64Release) {
        }
    }
})
