package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.MavenBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven

object LicensesHeaders : BuildType({
    name = "[Licenses Headers]"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
        ./dev-tools/IGNITE-*-*.patch => patch
    """.trimIndent()

    params {
        text("env.JAVA_HOME", "%env.JDK_ORA_8%", allowEmpty = true)
    }

    vcs {
        root(AbsoluteId("GitHubApacheIgnite"))

        cleanCheckout = true
    }

    steps {
        maven {
            name = "Check License Headers (by RAT)"
            goals = "validate"
            pomLocation = ""
            runnerArgs = """
                -DskipTests
                -Pcheck-licenses
            """.trimIndent()
            userSettingsSelection = "local-proxy.xml"
            userSettingsPath = "settings.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
            jdkHome = "%env.JDK_ORA_8%"
        }
    }
})
