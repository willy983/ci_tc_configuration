package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.VcsTrigger
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object RunBasicTests : BuildType({
    name = "--> Run :: Basic Tests"
    description = "Runs fastest test suites to get reply as fast as possible. Use -Run All for check changes"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(AbsoluteId("GitHubApacheIgnite"))

        showDependenciesChanges = true
    }

    triggers {
        vcs {
            quietPeriodMode = VcsTrigger.QuietPeriodMode.USE_DEFAULT
            branchFilter = """
                +:<default>
                +:pull/3667/head
                +:ignite-2.5
            """.trimIndent()
        }
    }

    dependencies {
        snapshot(Basic1) {
        }
        snapshot(BasicTestsWithPersistence) {
        }
        snapshot(CacheFullApi) {
        }
        snapshot(ComputeGrid) {
        }
        snapshot(InspectionsCore) {
        }
        snapshot(Javadoc) {
        }
        snapshot(LicensesHeaders) {
        }
        snapshot(MissingTests) {
        }
        snapshot(PdsUnitTests) {
        }
        snapshot(PlatformNetCoreLinux) {
        }
        snapshot(Security) {
        }
    }
})
