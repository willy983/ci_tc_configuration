package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object RunThinClient : BuildType({
    name = "-> Run :: Thin Client"
    description = "Run all Thin Client suites"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(AbsoluteId("GitHubApacheIgnite"))

        checkoutMode = CheckoutMode.ON_SERVER
        showDependenciesChanges = true
    }

    dependencies {
        snapshot(CheckCodeStyle) {
        }
        snapshot(JavaThinClient) {
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
        snapshot(ThinClientNodeJs) {
        }
        snapshot(ThinClientPhp) {
        }
        snapshot(WiPPlatformCCMakeWinX64Release) {
        }
        snapshot(WiPThinClientPython) {
        }
    }
})
