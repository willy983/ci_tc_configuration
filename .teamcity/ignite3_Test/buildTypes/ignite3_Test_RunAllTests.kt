package ignite3_Test.buildTypes

import _Self.vcsRoots.GitHubApacheIgnite3
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.schedule
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object ignite3_Test_RunAllTests : BuildType({
    name = "--> Run :: All Tests"

    type = BuildTypeSettings.Type.COMPOSITE

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite3)

        showDependenciesChanges = true
    }

    triggers {
        vcs {
            enabled = false
            branchFilter = "+:pull/*"
        }
        schedule {
            schedulingPolicy = daily {
                hour = 0
            }
            branchFilter = "+:<default>"
            triggerBuild = always()
            withPendingChangesOnly = false
            enableQueueOptimization = false
            param("cronExpression_hour", "12")
            param("cronExpression_dw", "*")
            param("cronExpression_min", "24")

            enforceCleanCheckoutForDependencies = true
            buildParams {
                param("reverse.dep.*.BUILD_NUMBER", "%build.number%")
            }
        }
    }

    features {
        commitStatusPublisher {
            enabled = false
            vcsRootExtId = "${GitHubApacheIgnite3.id}"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:47ba4fe5-5698-4573-92fc-ec69a81d9df4"
                }
            }
        }
    }

    dependencies {
        snapshot(ignite3_Test_RunIntegrationTests) {
        }
        snapshot(ignite3_Test_RunSanityChecks) {
        }
        snapshot(ignite3_Test_RunUnitTests) {
        }
    }
})
