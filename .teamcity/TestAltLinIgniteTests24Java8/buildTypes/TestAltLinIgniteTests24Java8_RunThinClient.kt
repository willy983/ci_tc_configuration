package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_RunThinClient : BuildType({
    name = "-> Run :: Thin Client"
    description = "Run all Thin Client suites"

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
        snapshot(TestAltLinIgniteTests24Java8_RunAllNet) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ThinClientJava) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ThinClientNodeJs) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ThinClientPhp) {
        }
        snapshot(TestAltLinIgniteTests24Java8_WiPThinClientPython) {
        }
    }
})
