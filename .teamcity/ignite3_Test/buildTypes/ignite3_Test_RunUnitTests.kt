package ignite3_Test.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.MavenBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

object ignite3_Test_RunUnitTests : BuildType({
    templates(ignite3.buildTypes.ignite3_ApacheIgniteBuildDependency)
    name = "> Run :: Unit Tests"

    steps {
        maven {
            name = "Run tests"
            id = "RUNNER_25"
            goals = "surefire:test"
            pomLocation = ""
            runnerArgs = """
                -Dmaven.test.failure.ignore=true
                -DfailIfNoTests=false
            """.trimIndent()
            userSettingsSelection = "local-proxy.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jdkHome = "%env.JDK_ORA_11%"
        }
    }

    triggers {
        vcs {
            id = "vcsTrigger"
            enabled = false
        }
    }

    features {
        pullRequests {
            id = "BUILD_EXT_1"
            vcsRootExtId = "Ignite3"
            provider = github {
                authType = token {
                    token = "credentialsJSON:4011fec0-e071-47f2-8259-f962d71f5c85"
                }
                filterTargetBranch = "+:refs/heads/main"
                filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
            }
        }
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_18")
    }
})
