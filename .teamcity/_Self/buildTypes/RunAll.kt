package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object RunAll : BuildType({
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
        root(AbsoluteId("GitHubApacheIgnite"))

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
        snapshot(ActivateDeactivateCluster) {
        }
        snapshot(Aop) {
        }
        snapshot(Aws) {
        }
        snapshot(Basic1) {
        }
        snapshot(Basic2) {
        }
        snapshot(BasicTestsWithPersistence) {
        }
        snapshot(BinaryObjects) {
        }
        snapshot(Cache1) {
        }
        snapshot(Cache2) {
        }
        snapshot(Cache3) {
        }
        snapshot(Cache4) {
        }
        snapshot(Cache5) {
        }
        snapshot(Cache6) {
        }
        snapshot(Cache7) {
        }
        snapshot(Cache8) {
        }
        snapshot(Cache9) {
        }
        snapshot(CacheDeadlockDetection) {
        }
        snapshot(CacheExpiryPolicy) {
        }
        snapshot(CacheFailover1) {
        }
        snapshot(CacheFailover2) {
        }
        snapshot(CacheFailover3) {
        }
        snapshot(CacheFailoverSsl) {
        }
        snapshot(CacheFullApi) {
        }
        snapshot(CacheFullApiMultiJvm) {
        }
        snapshot(CacheRestarts1) {
        }
        snapshot(CacheRestarts2) {
        }
        snapshot(CacheTxRecovery) {
        }
        snapshot(CassandraStore) {
        }
        snapshot(ClientNodes) {
        }
        snapshot(Cloud) {
        }
        snapshot(ComputeAffinityRun) {
        }
        snapshot(ComputeGrid) {
        }
        snapshot(ContinuousQuery1) {
        }
        snapshot(ContinuousQuery2) {
        }
        snapshot(ContinuousQuery3) {
        }
        snapshot(ContinuousQuery4) {
        }
        snapshot(ControlUtility) {
        }
        snapshot(ControlUtilityZookeeper) {
        }
        snapshot(DataStructures) {
        }
        snapshot(DevUtils) {
        }
        snapshot(Examples) {
        }
        snapshot(ExamplesLgpl) {
        }
        snapshot(Gce) {
        }
        snapshot(GeospatialIndexing) {
        }
        snapshot(Hibernate1) {
        }
        snapshot(Hibernate2) {
        }
        snapshot(Hibernate53) {
        }
        snapshot(InspectionsCore) {
        }
        snapshot(InterceptorCacheFullApiConfigVariationsBasic) {
        }
        snapshot(JCacheTck11) {
        }
        snapshot(JavaClient) {
        }
        snapshot(JavaThinClient) {
        }
        snapshot(Javadoc) {
        }
        snapshot(JdbcDriver) {
        }
        snapshot(Jta) {
        }
        snapshot(Kubernetes) {
        }
        snapshot(LicensesHeaders) {
        }
        snapshot(Logging) {
        }
        snapshot(MissingTests) {
        }
        snapshot(OpenCensus) {
        }
        snapshot(Pds1) {
        }
        snapshot(Pds2) {
        }
        snapshot(Pds3) {
        }
        snapshot(Pds4) {
        }
        snapshot(PdsCompatibility) {
        }
        snapshot(PdsIndexing) {
        }
        snapshot(PdsUnitTests) {
        }
        snapshot(PlatformCPPCMakeLinux) {
        }
        snapshot(PlatformCPPCMakeLinuxClang) {
        }
        snapshot(PlatformCWinX64Release) {
        }
        snapshot(PlatformNet) {
        }
        snapshot(PlatformNetCoreLinux) {
        }
        snapshot(PlatformNetIntegrations) {
        }
        snapshot(PlatformNetLongRunning) {
        }
        snapshot(PlatformNetNuGet) {
        }
        snapshot(Queries1) {
        }
        snapshot(Queries2) {
        }
        snapshot(QueriesConfigVariations) {
        }
        snapshot(Rdd) {
        }
        snapshot(ScalaExamples) {
        }
        snapshot(ScalaVisorConsole) {
        }
        snapshot(Security) {
        }
        snapshot(ServiceGrid) {
        }
        snapshot(ServiceGridLegacyMode) {
        }
        snapshot(Spi) {
        }
        snapshot(SpiUriDeploy) {
        }
        snapshot(Spring) {
        }
        snapshot(StartNodes) {
        }
        snapshot(Streamers) {
        }
        snapshot(ThinClientNodeJs) {
        }
        snapshot(ThinClientPhp) {
        }
        snapshot(WebSessions) {
        }
        snapshot(WiPPlatformCCMakeWinX64Release) {
        }
        snapshot(WiPThinClientPython) {
        }
        snapshot(Yarn) {
        }
        snapshot(ZooKeeper) {
        }
        snapshot(ZooKeeperDiscovery1) {
        }
        snapshot(ZooKeeperDiscovery2) {
        }
        snapshot(ZooKeeperDiscovery3) {
        }
        snapshot(ZooKeeperDiscovery4) {
        }
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux")
    }
})
