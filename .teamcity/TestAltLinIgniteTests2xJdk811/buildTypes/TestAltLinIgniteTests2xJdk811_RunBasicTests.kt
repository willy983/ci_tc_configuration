package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.VcsTrigger
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object TestAltLinIgniteTests2xJdk811_RunBasicTests : BuildType({
    name = "--> Run :: Basic Tests"
    description = "Runs fastest test suites to get reply as fast as possible. Use -Run All for check changes"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite)

        showDependenciesChanges = true
    }

    triggers {
        vcs {
            enabled = false
            quietPeriodMode = VcsTrigger.QuietPeriodMode.USE_DEFAULT
            branchFilter = """
                +:<default>
                +:pull/3667/head
                +:ignite-2.5
            """.trimIndent()
        }
    }

    dependencies {
        snapshot(TestAltLinIgniteTests2xJdk811_Basic1) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Basic3) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_CacheFullApi) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ComputeGrid) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_InspectionsCore) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Javadoc) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_LicensesHeaders) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_MissingTests) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PdsUnitTests) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PlatformNetCoreLinux) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Security) {
        }
    }
})
