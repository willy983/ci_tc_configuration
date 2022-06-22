package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object TestAltLinIgniteTests2xJdk811_RunAll : BuildType({
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
                
                curl "http://10.100.100.254:8088/rest/chainResults/html?serverId=public&buildId=%teamcity.build.id%" > report.html
            """.trimIndent()
        }
    }

    dependencies {
        snapshot(TestAltLinIgniteTests2xJdk811_ActivateDeactivateCluster) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Aop) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Basic1) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Basic2) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Basic3) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Basic4) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_BinaryObjects) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Cache1) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Cache10) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Cache11) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Cache12) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Cache13) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Cache2) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Cache3) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Cache4) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Cache5) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Cache6) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Cache7) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Cache8) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Cache9) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_CacheDeadlockDetection) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_CacheExpiryPolicy) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_CacheFailover1) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_CacheFailover2) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_CacheFailover3) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_CacheFailoverSsl) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_CacheFullApi) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_CacheFullApiMultiJvm) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_CacheFullApiMultiJvm2) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_CacheRestarts1) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_CacheRestarts2) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_CacheTxRecovery) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_CassandraStore) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_CheckCodeStyleDucktests) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ClientNodes) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Cloud) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ComputeAffinityRun) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ComputeGrid) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Consistency) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ContinuousQuery1) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ContinuousQuery2) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ContinuousQuery3) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ContinuousQuery4) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ControlUtility) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ControlUtilityZookeeper) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_DataStructures) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_DevUtils) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Examples) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ExamplesLgpl) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_GeospatialIndexing) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Hibernate42) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Hibernate51) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Hibernate53) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_IndexQueryApi) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_InspectionsCore) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_InterceptorCacheFullApiConfigVariationsBasic) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_InterceptorCacheFullApiConfigVariationsPeerClassLoading) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_JCacheTck11) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_JavaClient) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Javadoc) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_JdbcDriver) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Jta) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Kubernetes) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_LicensesHeaders) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Logging) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_MissingTests) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_NumaAllocator) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_OpenCensusNew) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Pds1) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Pds2) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Pds3) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Pds4) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Pds5) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Pds6) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Pds7) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Pds8) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PdsCompatibility) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PdsIndexing) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PdsUnitTests) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PlatformCCMakeWinX64Release) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PlatformCPPCMakeLinux) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PlatformCPPCMakeLinuxClang) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PlatformNetCoreLinux) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_PlatformNetWindows) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Queries1) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Queries1lazyTrue) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Queries2) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Queries2lazyTrue) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Queries3) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Queries3lazyTrue) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Queries4) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Queries4lazyTrue) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_QueriesConfigVariations) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Rdd) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ScalaExamples) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ScalaVisorConsole) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Security) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ServiceGrid) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Snapshots) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_SnapshotsWithIndexes) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Spi) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_SpiDiscovery) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_SpiUriDeploy) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Spring) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_StartNodes) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Streamers) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ThinClientJava) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ThinClientNodeJs) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ThinClientPhp) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_WebSessions) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_WiPThinClientPython) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_Yarn) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ZooKeeper) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ZooKeeperDiscovery1) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ZooKeeperDiscovery2) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ZooKeeperDiscovery3) {
        }
        snapshot(TestAltLinIgniteTests2xJdk811_ZooKeeperDiscovery4) {
        }
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux")
    }
})
