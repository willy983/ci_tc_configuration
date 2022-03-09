package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.VcsTrigger
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'TestAltLinIgniteTests2xJdk811_RunBasicTests'
in the project with id = 'TestAltLinIgniteTests2xJdk811', and delete the patch script.
*/
create(RelativeId("TestAltLinIgniteTests2xJdk811"), BuildType({
    id("TestAltLinIgniteTests2xJdk811_RunBasicTests")
    name = "--> Run :: Basic Tests"
    description = "Runs fastest test suites to get reply as fast as possible. Use -Run All for check changes"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(RelativeId("GitHubApacheIgnite"))

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
        snapshot(RelativeId("TestAltLinIgniteTests2xJdk811_Basic1")) {
        }
        snapshot(RelativeId("TestAltLinIgniteTests2xJdk811_Basic3")) {
        }
        snapshot(RelativeId("TestAltLinIgniteTests2xJdk811_CacheFullApi")) {
        }
        snapshot(RelativeId("TestAltLinIgniteTests2xJdk811_ComputeGrid")) {
        }
        snapshot(RelativeId("TestAltLinIgniteTests2xJdk811_InspectionsCore")) {
        }
        snapshot(RelativeId("TestAltLinIgniteTests2xJdk811_Javadoc")) {
        }
        snapshot(RelativeId("TestAltLinIgniteTests2xJdk811_LicensesHeaders")) {
        }
        snapshot(RelativeId("TestAltLinIgniteTests2xJdk811_MissingTests")) {
        }
        snapshot(RelativeId("TestAltLinIgniteTests2xJdk811_PdsUnitTests")) {
        }
        snapshot(RelativeId("TestAltLinIgniteTests2xJdk811_PlatformNetCoreLinux")) {
        }
        snapshot(RelativeId("TestAltLinIgniteTests2xJdk811_Security")) {
        }
    }
}))
