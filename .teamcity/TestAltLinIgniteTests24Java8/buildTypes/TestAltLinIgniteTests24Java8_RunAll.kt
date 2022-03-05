package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object TestAltLinIgniteTests24Java8_RunAll : BuildType({
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
        snapshot(TestAltLinIgniteTests24Java8_ActivateDeactivateCluster) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Aop) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Basic1) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Basic2) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Basic3) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Basic4) {
        }
        snapshot(TestAltLinIgniteTests24Java8_BinaryObjects) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Cache1) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Cache10) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Cache11) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Cache12) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Cache13) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Cache2) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Cache3) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Cache4) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Cache5) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Cache6) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Cache7) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Cache8) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Cache9) {
        }
        snapshot(TestAltLinIgniteTests24Java8_CacheDeadlockDetection) {
        }
        snapshot(TestAltLinIgniteTests24Java8_CacheExpiryPolicy) {
        }
        snapshot(TestAltLinIgniteTests24Java8_CacheFailover1) {
        }
        snapshot(TestAltLinIgniteTests24Java8_CacheFailover2) {
        }
        snapshot(TestAltLinIgniteTests24Java8_CacheFailover3) {
        }
        snapshot(TestAltLinIgniteTests24Java8_CacheFailoverSsl) {
        }
        snapshot(TestAltLinIgniteTests24Java8_CacheFullApi) {
        }
        snapshot(TestAltLinIgniteTests24Java8_CacheFullApiMultiJvm) {
        }
        snapshot(TestAltLinIgniteTests24Java8_CacheFullApiMultiJvm2) {
        }
        snapshot(TestAltLinIgniteTests24Java8_CacheRestarts1) {
        }
        snapshot(TestAltLinIgniteTests24Java8_CacheRestarts2) {
        }
        snapshot(TestAltLinIgniteTests24Java8_CacheTxRecovery) {
        }
        snapshot(TestAltLinIgniteTests24Java8_CassandraStore) {
        }
        snapshot(TestAltLinIgniteTests24Java8_CheckCodeStyleDucktests) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ClientNodes) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Cloud) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ComputeAffinityRun) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ComputeGrid) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Consistency) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ContinuousQuery1) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ContinuousQuery2) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ContinuousQuery3) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ContinuousQuery4) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ControlUtility) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ControlUtilityZookeeper) {
        }
        snapshot(TestAltLinIgniteTests24Java8_DataStructures) {
        }
        snapshot(TestAltLinIgniteTests24Java8_DevUtils) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Examples) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ExamplesLgpl) {
        }
        snapshot(TestAltLinIgniteTests24Java8_GeospatialIndexing) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Hibernate42) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Hibernate51) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Hibernate53) {
        }
        snapshot(TestAltLinIgniteTests24Java8_IndexQueryApi) {
        }
        snapshot(TestAltLinIgniteTests24Java8_InspectionsCore) {
        }
        snapshot(TestAltLinIgniteTests24Java8_InterceptorCacheFullApiConfigVariationsBasic) {
        }
        snapshot(TestAltLinIgniteTests24Java8_InterceptorCacheFullApiConfigVariationsPeerClassLoading) {
        }
        snapshot(TestAltLinIgniteTests24Java8_JCacheTck11) {
        }
        snapshot(TestAltLinIgniteTests24Java8_JavaClient) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Javadoc) {
        }
        snapshot(TestAltLinIgniteTests24Java8_JdbcDriver) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Jta) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Kubernetes) {
        }
        snapshot(TestAltLinIgniteTests24Java8_LicensesHeaders) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Logging) {
        }
        snapshot(TestAltLinIgniteTests24Java8_MissingTests) {
        }
        snapshot(TestAltLinIgniteTests24Java8_NumaAllocator) {
        }
        snapshot(TestAltLinIgniteTests24Java8_OpenCensusNew) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Pds1) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Pds2) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Pds3) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Pds4) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Pds5) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Pds6) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Pds7) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Pds8) {
        }
        snapshot(TestAltLinIgniteTests24Java8_PdsCompatibility) {
        }
        snapshot(TestAltLinIgniteTests24Java8_PdsIndexing) {
        }
        snapshot(TestAltLinIgniteTests24Java8_PdsUnitTests) {
        }
        snapshot(TestAltLinIgniteTests24Java8_PlatformCCMakeWinX64Release) {
        }
        snapshot(TestAltLinIgniteTests24Java8_PlatformCPPCMakeLinux) {
        }
        snapshot(TestAltLinIgniteTests24Java8_PlatformCPPCMakeLinuxClang) {
        }
        snapshot(TestAltLinIgniteTests24Java8_PlatformNetCoreLinux) {
        }
        snapshot(TestAltLinIgniteTests24Java8_PlatformNetWindows) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Queries1) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Queries1lazyTrue) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Queries2) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Queries2lazyTrue) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Queries3) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Queries3lazyTrue) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Queries4) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Queries4lazyTrue) {
        }
        snapshot(TestAltLinIgniteTests24Java8_QueriesConfigVariations) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Rdd) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ScalaExamples) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ScalaVisorConsole) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Security) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ServiceGrid) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Snapshots) {
        }
        snapshot(TestAltLinIgniteTests24Java8_SnapshotsWithIndexes) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Spi) {
        }
        snapshot(TestAltLinIgniteTests24Java8_SpiDiscovery) {
        }
        snapshot(TestAltLinIgniteTests24Java8_SpiUriDeploy) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Spring) {
        }
        snapshot(TestAltLinIgniteTests24Java8_StartNodes) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Streamers) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ThinClientJava) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ThinClientNodeJs) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ThinClientPhp) {
        }
        snapshot(TestAltLinIgniteTests24Java8_WebSessions) {
        }
        snapshot(TestAltLinIgniteTests24Java8_WiPThinClientPython) {
        }
        snapshot(TestAltLinIgniteTests24Java8_Yarn) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ZooKeeper) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ZooKeeperDiscovery1) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ZooKeeperDiscovery2) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ZooKeeperDiscovery3) {
        }
        snapshot(TestAltLinIgniteTests24Java8_ZooKeeperDiscovery4) {
        }
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux")
    }
})
