package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.schedule

object TestAltLinIgniteTests2xJdk811_RunAllNightly : BuildType({
    name = "--> Run :: All (Nightly)"
    description = "Dummy build for run all build in project by one click"

    artifactRules = "report.html"
    type = BuildTypeSettings.Type.COMPOSITE

    params {
        checkbox("reverse.dep.*.IGNITE_LOGGING_OPTS", "-DIGNITE_TEST_PROP_LOG4J_FILE=log4j-tc-test.xml -DIGNITE_QUIET=true", label = "Quite console output", display = ParameterDisplay.PROMPT,
                  checked = "-DIGNITE_TEST_PROP_LOG4J_FILE=log4j-tc-test.xml -DIGNITE_QUIET=true", unchecked = "-DIGNITE_QUIET=false")
        text("reverse.dep.*.TEST_SCALE_FACTOR", "1.0", allowEmpty = true)
        select("reverse.dep.*.env.JAVA_HOME", "%env.JDK_ORA_8%", label = "JDK version", description = "Select JDK version for all tests",
                options = listOf("JDK 8" to "%env.JDK_ORA_8%", "JDK 11" to "%env.JDK_OPEN_11%"))
    }

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite)

        checkoutMode = CheckoutMode.ON_SERVER
        excludeDefaultBranchChanges = true
        showDependenciesChanges = true
    }

    steps {
        script {
            scriptContent = """
                #!/usr/bin/env bash
                set -x
                
                curl "http://10.100.100.254:8088/rest/chainResults/html?serverId=public&buildId=%teamcity.build.id%" > report.html
            """.trimIndent()
        }
    }

    triggers {
        schedule {
            enabled = false
            schedulingPolicy = daily {
                hour = 4
            }
            branchFilter = "+:pull/5203/head"
            triggerBuild = always()
            enableQueueOptimization = false
            param("revisionRuleBuildBranch", "<default>")

            enforceCleanCheckoutForDependencies = true
        }
        schedule {
            enabled = false
            schedulingPolicy = daily {
                hour = 2
            }
            branchFilter = """
                +:pull/5480/head
                +:pull/8508/head
            """.trimIndent()
            triggerBuild = always()
            enableQueueOptimization = false
            param("revisionRuleBuildBranch", "<default>")

            enforceCleanCheckoutForDependencies = true
        }
        schedule {
            enabled = false
            schedulingPolicy = daily {
                hour = 1
                minute = 30
            }
            branchFilter = "+:ignite-2.12"
            triggerBuild = always()
            withPendingChangesOnly = false
            enableQueueOptimization = false
            param("revisionRuleBuildBranch", "<default>")
        }
    }

    dependencies {
        snapshot(TestAltLinIgniteTests2xJdk811_BinaryObjectsSimpleMapperBasic) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_BinaryObjectsSimpleMapperCacheFullApi) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_BinaryObjectsSimpleMapperComputeGrid) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_CacheFullApiConfigVariationsBasic) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_CacheFullApiConfigVariationsWithKeepBinary) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ContinuousQueryConfigVariations) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_DiskPageCompressions1) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_DiskPageCompressions2) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_MemoryLeaks) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Ml) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PdsDirectIo1) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PdsDirectIo2) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_QueriesBinaryObjectsSimpleMapper) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_RunAll) {
        }
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux")
    }
})
