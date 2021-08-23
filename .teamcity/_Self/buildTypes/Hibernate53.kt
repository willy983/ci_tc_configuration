package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object Hibernate53 : BuildType({
    templates(RunTestSuitesJava)
    name = "Hibernate 5.3"

    artifactRules = """
        work/log => logs.zip
        **/hs_err*.log => crashdumps.zip
        **/core => crashdumps.zip
        ./**/target/rat.txt => rat.zip
        ./dev-tools/IGNITE-*-*.patch => patch
        /home/teamcity/ignite-startNodes/*.log => ignite-startNodes.zip
    """.trimIndent()

    params {
        param("MAVEN_MODULES", ":ignite-hibernate_5.3")
        param("TEST_SUITE", "IgniteHibernate53TestSuite")
    }

    steps {
        script {
            name = "[WA] Hibernate 5.3 work around for old branches"
            id = "RUNNER_1"
            scriptContent = """
                #!/usr/bin/env bash
                set -x
                
                
                MAVEN_MODULES="%MAVEN_MODULES%"
                if ! [ -d modules/hibernate-5.3 ]; then
                    MAVEN_MODULES=":ignite-core"
                fi
                
                set +x
                echo "##teamcity[setParameter name='MAVEN_MODULES' value='${'$'}{MAVEN_MODULES}']"
            """.trimIndent()
        }
        stepsOrder = arrayListOf("RUNNER_1", "RUNNER_264", "RUNNER_287", "RUNNER_225", "RUNNER_265", "RUNNER_266")
    }

    failureConditions {
        executionTimeoutMin = 20
    }
})
