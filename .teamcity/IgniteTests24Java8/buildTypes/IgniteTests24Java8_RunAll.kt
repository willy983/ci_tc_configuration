package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object IgniteTests24Java8_RunAll : BuildType({
    name = "--> Run :: All"
    description = "Dummy build for run all build in project by one click"

    artifactRules = "report.html"
    type = BuildTypeSettings.Type.COMPOSITE

    params {
        checkbox("reverse.dep.*.IGNITE_LOGGING_OPTS", "-DIGNITE_TEST_PROP_LOG4J_FILE=log4j-tc-test.xml -DIGNITE_QUIET=true", label = "Quite console output", display = ParameterDisplay.PROMPT,
                  checked = "-DIGNITE_TEST_PROP_LOG4J_FILE=log4j-tc-test.xml -DIGNITE_QUIET=true", unchecked = "-DIGNITE_QUIET=false")
        text("reverse.dep.*.TEST_SCALE_FACTOR", "0.1", label = "Test scale factor", allowEmpty = true)
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
                
                curl "http://172.25.5.21:8080/rest/chainResults/html?serverId=public&buildId=%teamcity.build.id%" > report.html
            """.trimIndent()
        }
    }

    dependencies {
        snapshot(IgniteTests24Java8_ActivateDeactivateCluster) {
        }
        snapshot(IgniteTests24Java8_Aop) {
        }
        snapshot(IgniteTests24Java8_Aws) {
        }
        snapshot(IgniteTests24Java8_Basic1) {
        }
        snapshot(IgniteTests24Java8_Basic2) {
        }
        snapshot(IgniteTests24Java8_BasicTestsWithPersistence) {
        }
        snapshot(IgniteTests24Java8_BinaryObjects) {
        }
        snapshot(IgniteTests24Java8_Cache1) {
        }
        snapshot(IgniteTests24Java8_Cache2) {
        }
        snapshot(IgniteTests24Java8_Cache3) {
        }
        snapshot(IgniteTests24Java8_Cache4) {
        }
        snapshot(IgniteTests24Java8_Cache5) {
        }
        snapshot(IgniteTests24Java8_Cache6) {
        }
        snapshot(IgniteTests24Java8_Cache7) {
        }
        snapshot(IgniteTests24Java8_Cache8) {
        }
        snapshot(IgniteTests24Java8_Cache9) {
        }
        snapshot(IgniteTests24Java8_CacheDeadlockDetection) {
        }
        snapshot(IgniteTests24Java8_CacheExpiryPolicy) {
        }
        snapshot(IgniteTests24Java8_CacheFailover1) {
        }
        snapshot(IgniteTests24Java8_CacheFailover2) {
        }
        snapshot(IgniteTests24Java8_CacheFailover3) {
        }
        snapshot(IgniteTests24Java8_CacheFailoverSsl) {
        }
        snapshot(IgniteTests24Java8_CacheFullApi) {
        }
        snapshot(IgniteTests24Java8_CacheFullApiMultiJvm) {
        }
        snapshot(IgniteTests24Java8_CacheRestarts1) {
        }
        snapshot(IgniteTests24Java8_CacheRestarts2) {
        }
        snapshot(IgniteTests24Java8_CacheTxRecovery) {
        }
        snapshot(IgniteTests24Java8_CassandraStore) {
        }
        snapshot(IgniteTests24Java8_ClientNodes) {
        }
        snapshot(IgniteTests24Java8_Cloud) {
        }
        snapshot(IgniteTests24Java8_ComputeAffinityRun) {
        }
        snapshot(IgniteTests24Java8_ComputeGrid) {
        }
        snapshot(IgniteTests24Java8_ContinuousQuery1) {
        }
        snapshot(IgniteTests24Java8_ContinuousQuery2) {
        }
        snapshot(IgniteTests24Java8_ContinuousQuery3) {
        }
        snapshot(IgniteTests24Java8_ContinuousQuery4) {
        }
        snapshot(IgniteTests24Java8_ControlUtility) {
        }
        snapshot(IgniteTests24Java8_ControlUtilityZookeeper) {
        }
        snapshot(IgniteTests24Java8_DataStructures) {
        }
        snapshot(IgniteTests24Java8_DevUtils) {
        }
        snapshot(IgniteTests24Java8_Examples) {
        }
        snapshot(IgniteTests24Java8_ExamplesLgpl) {
        }
        snapshot(IgniteTests24Java8_Gce) {
        }
        snapshot(IgniteTests24Java8_GeospatialIndexing) {
        }
        snapshot(IgniteTests24Java8_Hibernate1) {
        }
        snapshot(IgniteTests24Java8_Hibernate2) {
        }
        snapshot(IgniteTests24Java8_Hibernate53) {
        }
        snapshot(IgniteTests24Java8_InspectionsCore) {
        }
        snapshot(IgniteTests24Java8_InterceptorCacheFullApiConfigVariationsBasic) {
        }
        snapshot(IgniteTests24Java8_JCacheTck11) {
        }
        snapshot(IgniteTests24Java8_JavaClient) {
        }
        snapshot(IgniteTests24Java8_JavaThinClient) {
        }
        snapshot(IgniteTests24Java8_Javadoc) {
        }
        snapshot(IgniteTests24Java8_JdbcDriver) {
        }
        snapshot(IgniteTests24Java8_Jta) {
        }
        snapshot(IgniteTests24Java8_Kubernetes) {
        }
        snapshot(IgniteTests24Java8_LicensesHeaders) {
        }
        snapshot(IgniteTests24Java8_Logging) {
        }
        snapshot(IgniteTests24Java8_MissingTests) {
        }
        snapshot(IgniteTests24Java8_OpenCensus) {
        }
        snapshot(IgniteTests24Java8_Pds1) {
        }
        snapshot(IgniteTests24Java8_Pds2) {
        }
        snapshot(IgniteTests24Java8_Pds3) {
        }
        snapshot(IgniteTests24Java8_Pds4) {
        }
        snapshot(IgniteTests24Java8_PdsCompatibility) {
        }
        snapshot(IgniteTests24Java8_PdsIndexing) {
        }
        snapshot(IgniteTests24Java8_PdsUnitTests) {
        }
        snapshot(IgniteTests24Java8_PlatformCPPCMakeLinux) {
        }
        snapshot(IgniteTests24Java8_PlatformCPPCMakeLinuxClang) {
        }
        snapshot(IgniteTests24Java8_PlatformCWinX64Release) {
        }
        snapshot(IgniteTests24Java8_PlatformNet) {
        }
        snapshot(IgniteTests24Java8_PlatformNetCoreLinux) {
        }
        snapshot(IgniteTests24Java8_PlatformNetIntegrations) {
        }
        snapshot(IgniteTests24Java8_PlatformNetLongRunning) {
        }
        snapshot(IgniteTests24Java8_PlatformNetNuGet) {
        }
        snapshot(IgniteTests24Java8_Queries1) {
        }
        snapshot(IgniteTests24Java8_Queries2) {
        }
        snapshot(IgniteTests24Java8_QueriesConfigVariations) {
        }
        snapshot(IgniteTests24Java8_Rdd) {
        }
        snapshot(IgniteTests24Java8_ScalaExamples) {
        }
        snapshot(IgniteTests24Java8_ScalaVisorConsole) {
        }
        snapshot(IgniteTests24Java8_Security) {
        }
        snapshot(IgniteTests24Java8_ServiceGrid) {
        }
        snapshot(IgniteTests24Java8_ServiceGridLegacyMode) {
        }
        snapshot(IgniteTests24Java8_Spi) {
        }
        snapshot(IgniteTests24Java8_SpiUriDeploy) {
        }
        snapshot(IgniteTests24Java8_Spring) {
        }
        snapshot(IgniteTests24Java8_StartNodes) {
        }
        snapshot(IgniteTests24Java8_Streamers) {
        }
        snapshot(IgniteTests24Java8_ThinClientNodeJs) {
        }
        snapshot(IgniteTests24Java8_ThinClientPhp) {
        }
        snapshot(IgniteTests24Java8_WebSessions) {
        }
        snapshot(IgniteTests24Java8_WiPPlatformCCMakeWinX64Release) {
        }
        snapshot(IgniteTests24Java8_WiPThinClientPython) {
        }
        snapshot(IgniteTests24Java8_Yarn) {
        }
        snapshot(IgniteTests24Java8_ZooKeeper) {
        }
        snapshot(IgniteTests24Java8_ZooKeeperDiscovery1) {
        }
        snapshot(IgniteTests24Java8_ZooKeeperDiscovery2) {
        }
        snapshot(IgniteTests24Java8_ZooKeeperDiscovery3) {
        }
        snapshot(IgniteTests24Java8_ZooKeeperDiscovery4) {
        }
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux")
    }
})
