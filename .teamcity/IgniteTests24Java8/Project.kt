package IgniteTests24Java8

import IgniteTests24Java8.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project

object Project : Project({
    id("IgniteTests24Java8")
    name = "Ignite Tests 2.x (JDK 8/11)"
    description = "Project for Ignite 2.x testing with JDK 8 as default"

    buildType(IgniteTests24Java8_CacheFullApiMultiJvm)
    buildType(IgniteTests24Java8_Spi)
    buildType(IgniteTests24Java8_PlatformCPPLinuxClang)
    buildType(IgniteTests24Java8_OpenCensus)
    buildType(IgniteTests24Java8_CacheRestarts1)
    buildType(IgniteTests24Java8_CacheRestarts2)
    buildType(IgniteTests24Java8_DataStructures)
    buildType(IgniteTests24Java8_BuildApacheIgnite)
    buildType(IgniteTests24Java8_ActivateDeactivateCluster)
    buildType(IgniteTests24Java8_PdsUnitTests)
    buildType(IgniteTests24Java8_Basic1)
    buildType(IgniteTests24Java8_MvccQueries)
    buildType(IgniteTests24Java8_Basic2)
    buildType(IgniteTests24Java8_DevUtils)
    buildType(IgniteTests24Java8_ComputeGrid)
    buildType(IgniteTests24Java8_ZooKeeperDiscovery1)
    buildType(IgniteTests24Java8_RunMl)
    buildType(IgniteTests24Java8_Build)
    buildType(IgniteTests24Java8_ZooKeeperDiscovery4)
    buildType(IgniteTests24Java8_ZooKeeperDiscovery3)
    buildType(IgniteTests24Java8_ZooKeeperDiscovery2)
    buildType(IgniteTests24Java8_ContinuousQuery4)
    buildType(IgniteTests24Java8_RunBasicTests)
    buildType(IgniteTests24Java8_CacheFullApiConfigVariations)
    buildType(IgniteTests24Java8_ContinuousQuery3)
    buildType(IgniteTests24Java8_ContinuousQuery2)
    buildType(IgniteTests24Java8_CacheFullApi)
    buildType(IgniteTests24Java8_ContinuousQuery1)
    buildType(IgniteTests24Java8_PlatformNetNuGet)
    buildType(IgniteTests24Java8_ExamplesLgpl)
    buildType(IgniteTests24Java8_BinaryObjectsSimpleMapperBasic)
    buildType(IgniteTests24Java8_CacheFullApiBasicConfigVariations)
    buildType(IgniteTests24Java8_JCacheTck11)
    buildType(IgniteTests24Java8_MissingTests)
    buildType(IgniteTests24Java8_ThinClientPhpOld)
    buildType(IgniteTests24Java8_Pds4)
    buildType(IgniteTests24Java8_SpiUriDeploy)
    buildType(IgniteTests24Java8_Pds3)
    buildType(IgniteTests24Java8_Pds2)
    buildType(IgniteTests24Java8_Pds1)
    buildType(IgniteTests24Java8_ClientNodes)
    buildType(IgniteTests24Java8_BinaryObjectsSimpleMapperCacheFullApi)
    buildType(IgniteTests24Java8_CacheFailover2)
    buildType(IgniteTests24Java8_CacheFailover3)
    buildType(IgniteTests24Java8_ControlUtility)
    buildType(IgniteTests24Java8_PlatformNetCore30Linux)
    buildType(IgniteTests24Java8_Aws)
    buildType(IgniteTests24Java8_OnDemandAssembly)
    buildType(IgniteTests24Java8_Kubernetes)
    buildType(IgniteTests24Java8_BinaryObjectsSimpleMapperQueries)
    buildType(IgniteTests24Java8_PlatformNetCoreLinux)
    buildType(IgniteTests24Java8_MemoryLeaks)
    buildType(IgniteTests24Java8_ServiceGrid)
    buildType(IgniteTests24Java8_Javadoc)
    buildType(IgniteTests24Java8_ScalaExamples)
    buildType(IgniteTests24Java8_GeospatialIndexing)
    buildType(IgniteTests24Java8_Hibernate2)
    buildType(IgniteTests24Java8_RunThinClient)
    buildType(IgniteTests24Java8_Cloud)
    buildType(IgniteTests24Java8_Hibernate1)
    buildType(IgniteTests24Java8_QueriesConfigVariations)
    buildType(IgniteTests24Java8_Aop)
    buildType(IgniteTests24Java8_PlatformCWinX64Debug)
    buildType(IgniteTests24Java8_Gce)
    buildType(IgniteTests24Java8_JdbcDriver)
    buildType(IgniteTests24Java8_CheckCodeStyle)
    buildType(IgniteTests24Java8_Queries2lazyFalse)
    buildType(IgniteTests24Java8_BinaryObjects)
    buildType(IgniteTests24Java8_PdsCompatibility)
    buildType(IgniteTests24Java8_MvccCache9)
    buildType(IgniteTests24Java8_MvccCache7)
    buildType(IgniteTests24Java8_RunAllNightly)
    buildType(IgniteTests24Java8_MvccCache8)
    buildType(IgniteTests24Java8_Spring)
    buildType(IgniteTests24Java8_MvccCache1)
    buildType(IgniteTests24Java8_MvccPds3)
    buildType(IgniteTests24Java8_PlatformNetLongRunning)
    buildType(IgniteTests24Java8_MvccCache2)
    buildType(IgniteTests24Java8_MvccPds2)
    buildType(IgniteTests24Java8_MvccPds1)
    buildType(IgniteTests24Java8_MvccCache5)
    buildType(IgniteTests24Java8_ComputeAffinityRun)
    buildType(IgniteTests24Java8_Igfs)
    buildType(IgniteTests24Java8_MvccCache6)
    buildType(IgniteTests24Java8_CalciteSql)
    buildType(IgniteTests24Java8_MvccCache4)
    buildType(IgniteTests24Java8_RunAllPds)
    buildType(IgniteTests24Java8_JavaThinClient)
    buildType(IgniteTests24Java8_Logging)
    buildType(IgniteTests24Java8_ZooKeeper)
    buildType(IgniteTests24Java8_DiskPageCompressions)
    buildType(IgniteTests24Java8_ServiceGridLegacyMode)
    buildType(IgniteTests24Java8_InspectionsCore)
    buildType(IgniteTests24Java8_ExcludedMvccCache3)
    buildType(IgniteTests24Java8_IgfsLinuxAndMacOS)
    buildType(IgniteTests24Java8_PlatformNetInspections)
    buildType(IgniteTests24Java8_RunMlTensorFlow)
    buildType(IgniteTests24Java8_ExchangeDoesnTBlockReadOperation)
    buildType(IgniteTests24Java8_Queries2)
    buildType(IgniteTests24Java8_Queries1)
    buildType(IgniteTests24Java8_StartNodes)
    buildType(IgniteTests24Java8_Queries1lazyTrue)
    buildType(IgniteTests24Java8_Cache9)
    buildType(IgniteTests24Java8_CacheFailover1)
    buildType(IgniteTests24Java8_Cache8)
    buildType(IgniteTests24Java8_PlatformCPPCMakeLinux)
    buildType(IgniteTests24Java8_ThinClientNodeJsOld)
    buildType(IgniteTests24Java8_RunMvccCache)
    buildType(IgniteTests24Java8_ScalaVisorConsole)
    buildType(IgniteTests24Java8_Cache3)
    buildType(IgniteTests24Java8_Test)
    buildType(IgniteTests24Java8_Cache2)
    buildType(IgniteTests24Java8_Cache1)
    buildType(IgniteTests24Java8_Cache7)
    buildType(IgniteTests24Java8_Hibernate53)
    buildType(IgniteTests24Java8_Cache6)
    buildType(IgniteTests24Java8_SpiWindows)
    buildType(IgniteTests24Java8_ThinClientPhp)
    buildType(IgniteTests24Java8_Cache5)
    buildType(IgniteTests24Java8_CacheTxRecovery)
    buildType(IgniteTests24Java8_Cache4)
    buildType(IgniteTests24Java8_Jta)
    buildType(IgniteTests24Java8_MvccPds4)
    buildType(IgniteTests24Java8_PlatformNet)
    buildType(IgniteTests24Java8_WebSessions)
    buildType(IgniteTests24Java8_ThinClientPython)
    buildType(IgniteTests24Java8_Ml)
    buildType(IgniteTests24Java8_MvccCache_2)
    buildType(IgniteTests24Java8_ThinClientPythonOld)
    buildType(IgniteTests24Java8_MvccCache)
    buildType(IgniteTests24Java8_WiPThinClientPython)
    buildType(IgniteTests24Java8_ControlUtilityZookeeper)
    buildType(IgniteTests24Java8_Security)
    buildType(IgniteTests24Java8_LicensesHeaders)
    buildType(IgniteTests24Java8_ThinClientNodeJs)
    buildType(IgniteTests24Java8_PlatformCWinX64Release)
    buildType(IgniteTests24Java8_CacheExpiryPolicy)
    buildType(IgniteTests24Java8_RunCpp)
    buildType(IgniteTests24Java8_PdsDirectIo1)
    buildType(IgniteTests24Java8_RunAll)
    buildType(IgniteTests24Java8_Rdd)
    buildType(IgniteTests24Java8_BinaryObjectsSimpleMapperComputeGrid)
    buildType(IgniteTests24Java8_ContinuousQueryConfigVariations)
    buildType(IgniteTests24Java8_PdsDirectIo2)
    buildType(IgniteTests24Java8_PlatformNetIntegrations)
    buildType(IgniteTests24Java8_PlatformCMac)
    buildType(IgniteTests24Java8_JavaClient)
    buildType(IgniteTests24Java8_RunAllNet)
    buildType(IgniteTests24Java8_CacheDeadlockDetection)
    buildType(IgniteTests24Java8_CacheFailoverSsl)
    buildType(IgniteTests24Java8_PdsIndexing)
    buildType(IgniteTests24Java8_Yarn)
    buildType(IgniteTests24Java8_CassandraStore)
    buildType(IgniteTests24Java8_PlatformCLinux)
    buildType(IgniteTests24Java8_RunCache)
    buildType(IgniteTests24Java8_PlatformCCMakeWinX64Debug)
    buildType(IgniteTests24Java8_TensorFlow)
    buildType(IgniteTests24Java8_PlatformCPPCMakeLinuxClang)
    buildType(IgniteTests24Java8_Hadoop)
    buildType(IgniteTests24Java8_InterceptorCacheFullApiConfigVariationsBasic)
    buildType(IgniteTests24Java8_BasicTestsWithPersistence)
    buildType(IgniteTests24Java8_PlatformNetWindows)
    buildType(IgniteTests24Java8_Streamers)
    buildType(IgniteTests24Java8_WiPPlatformCCMakeWinX64Release)
    buildType(IgniteTests24Java8_Examples)

    template(IgniteTests24Java8_C)
    template(IgniteTests24Java8_PostBuild)
    template(IgniteTests24Java8_CLinux)
    template(IgniteTests24Java8_RunTestSuitesNet)
    template(IgniteTests24Java8_ExcludeTests)
    template(IgniteTests24Java8_RunIntelliJIdeaInspectionsTemplate)
    template(IgniteTests24Java8_ThirdpartyCheckout)
    template(IgniteTests24Java8_PreBuild)
    template(IgniteTests24Java8_RunTestSuitesJava)

    params {
        text("system.IGNITE_DUMP_THREADS_ON_FAILURE", "false", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("env.MALLOC_ARENA_MAX", "4", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_PROFILES", "all-java,all-scala,all-other,compatibility,lgpl,yardstick,benchmarks,examples", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        param("system.SKIP_BUILD", "false")
    }

    cleanup {
        baseRule {
            all(days = 14)
        }
    }

    subProject(IgniteTests24Java8_Dev.Project)
})
