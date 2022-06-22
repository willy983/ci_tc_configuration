package TestAltLinIgniteTests2xJdk811

import TestAltLinIgniteTests2xJdk811.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.CustomChart
import jetbrains.buildServer.configs.kotlin.v2019_2.CustomChart.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.buildTypeCustomChart

object Project : Project({
    id("TestAltLinIgniteTests2xJdk811")
    name = "TestAltLinIgnite Tests 2.x (JDK 8/11)"
    description = "Project for Ignite 2.x testing with JDK 8 as default"

    buildType(TestAltLinIgniteTests2xJdk811_Javadoc)
    buildType(TestAltLinIgniteTests2xJdk811_ZooKeeperDiscovery2)
    buildType(TestAltLinIgniteTests2xJdk811_ZooKeeperDiscovery1)
    buildType(TestAltLinIgniteTests2xJdk811_ZooKeeperDiscovery4)
    buildType(TestAltLinIgniteTests2xJdk811_ZooKeeperDiscovery3)
    buildType(TestAltLinIgniteTests2xJdk811_CacheFullApiMultiJvm)
    buildType(TestAltLinIgniteTests2xJdk811_IndexQueryApi)
    buildType(TestAltLinIgniteTests2xJdk811_Streamers)
    buildType(TestAltLinIgniteTests2xJdk811_ServiceGridLegacyMode)
    buildType(TestAltLinIgniteTests2xJdk811_Pds5)
    buildType(TestAltLinIgniteTests2xJdk811_MissingTests)
    buildType(TestAltLinIgniteTests2xJdk811_JavaClient)
    buildType(TestAltLinIgniteTests2xJdk811_Pds4)
    buildType(TestAltLinIgniteTests2xJdk811_Pds3)
    buildType(TestAltLinIgniteTests2xJdk811_Pds2)
    buildType(TestAltLinIgniteTests2xJdk811_CheckCodeStyle)
    buildType(TestAltLinIgniteTests2xJdk811_InterceptorCacheFullApiConfigVariationsPeerClassLoading)
    buildType(TestAltLinIgniteTests2xJdk811_Pds8)
    buildType(TestAltLinIgniteTests2xJdk811_Snapshots)
    buildType(TestAltLinIgniteTests2xJdk811_Pds7)
    buildType(TestAltLinIgniteTests2xJdk811_Pds6)
    buildType(TestAltLinIgniteTests2xJdk811_CacheFullApiConfigVariationsBasic)
    buildType(TestAltLinIgniteTests2xJdk811_Pds1)
    buildType(TestAltLinIgniteTests2xJdk811_RunCpp)
    buildType(TestAltLinIgniteTests2xJdk811_Queries3lazyTrue)
    buildType(TestAltLinIgniteTests2xJdk811_RunAllNightly)
    buildType(TestAltLinIgniteTests2xJdk811_Yarn)
    buildType(TestAltLinIgniteTests2xJdk811_RunAll)
    buildType(TestAltLinIgniteTests2xJdk811_QueriesBinaryObjectsSimpleMapper)
    buildType(TestAltLinIgniteTests2xJdk811_ScalaExamples)
    buildType(TestAltLinIgniteTests2xJdk811_RunThinClient)
    buildType(TestAltLinIgniteTests2xJdk811_ZooKeeper)
    buildType(TestAltLinIgniteTests2xJdk811_BinaryObjects)
    buildType(TestAltLinIgniteTests2xJdk811_SpiDiscovery)
    buildType(TestAltLinIgniteTests2xJdk811_BinaryObjectsSimpleMapperBasic)
    buildType(TestAltLinIgniteTests2xJdk811_PdsCompatibility)
    buildType(TestAltLinIgniteTests2xJdk811_Hibernate42)
    buildType(TestAltLinIgniteTests2xJdk811_Queries2)
    buildType(TestAltLinIgniteTests2xJdk811_PlatformCPPCMakeLinux)
    buildType(TestAltLinIgniteTests2xJdk811_Queries1)
    buildType(TestAltLinIgniteTests2xJdk811_Queries4)
    buildType(TestAltLinIgniteTests2xJdk811_Consistency)
    buildType(TestAltLinIgniteTests2xJdk811_Queries3)
    buildType(TestAltLinIgniteTests2xJdk811_CacheFullApi)
    buildType(TestAltLinIgniteTests2xJdk811_InterceptorCacheFullApiConfigVariationsBasic)
    buildType(TestAltLinIgniteTests2xJdk811_QueriesConfigVariations)
    buildType(TestAltLinIgniteTests2xJdk811_ActivateDeactivateCluster)
    buildType(TestAltLinIgniteTests2xJdk811_ContinuousQueryConfigVariations)
    buildType(TestAltLinIgniteTests2xJdk811_BinaryObjectsSimpleMapperComputeGrid)
    buildType(TestAltLinIgniteTests2xJdk811_ClientNodes)
    buildType(TestAltLinIgniteTests2xJdk811_Cloud)
    buildType(TestAltLinIgniteTests2xJdk811_JCacheTck11)
    buildType(TestAltLinIgniteTests2xJdk811_CacheFailover3)
    buildType(TestAltLinIgniteTests2xJdk811_ScalaVisorConsole)
    buildType(TestAltLinIgniteTests2xJdk811_CacheFailover1)
    buildType(TestAltLinIgniteTests2xJdk811_WiPThinClientPython)
    buildType(TestAltLinIgniteTests2xJdk811_CacheFailover2)
    buildType(TestAltLinIgniteTests2xJdk811_Hibernate51)
    buildType(TestAltLinIgniteTests2xJdk811_Queries1lazyTrue)
    buildType(TestAltLinIgniteTests2xJdk811_Hibernate53)
    buildType(TestAltLinIgniteTests2xJdk811_PdsUnitTests)
    buildType(TestAltLinIgniteTests2xJdk811_PlatformCCMakeWinX64Debug)
    buildType(TestAltLinIgniteTests2xJdk811_BuildApacheIgnite)
    buildType(TestAltLinIgniteTests2xJdk811_Queries4lazyTrue)
    buildType(TestAltLinIgniteTests2xJdk811_CacheFullApiMultiJvm2)
    buildType(TestAltLinIgniteTests2xJdk811_ExamplesLgpl)
    buildType(TestAltLinIgniteTests2xJdk811_ThinClientPhp)
    buildType(TestAltLinIgniteTests2xJdk811_LicensesHeaders)
    buildType(TestAltLinIgniteTests2xJdk811_Jta)
    buildType(TestAltLinIgniteTests2xJdk811_NumaAllocator)
    buildType(TestAltLinIgniteTests2xJdk811_PlatformNetWindows)
    buildType(TestAltLinIgniteTests2xJdk811_Rdd)
    buildType(TestAltLinIgniteTests2xJdk811_SpiUriDeploy)
    buildType(TestAltLinIgniteTests2xJdk811_ThinClientJava)
    buildType(TestAltLinIgniteTests2xJdk811_Cache13)
    buildType(TestAltLinIgniteTests2xJdk811_Cache12)
    buildType(TestAltLinIgniteTests2xJdk811_Cache11)
    buildType(TestAltLinIgniteTests2xJdk811_Cache10)
    buildType(TestAltLinIgniteTests2xJdk811_MemoryLeaks)
    buildType(TestAltLinIgniteTests2xJdk811_ContinuousQuery3)
    buildType(TestAltLinIgniteTests2xJdk811_ContinuousQuery2)
    buildType(TestAltLinIgniteTests2xJdk811_GeospatialIndexing)
    buildType(TestAltLinIgniteTests2xJdk811_OpenCensusNew)
    buildType(TestAltLinIgniteTests2xJdk811_ContinuousQuery1)
    buildType(TestAltLinIgniteTests2xJdk811_CalciteSql)
    buildType(TestAltLinIgniteTests2xJdk811_CacheExpiryPolicy)
    buildType(TestAltLinIgniteTests2xJdk811_Ml)
    buildType(TestAltLinIgniteTests2xJdk811_PdsDirectIo2)
    buildType(TestAltLinIgniteTests2xJdk811_PdsDirectIo1)
    buildType(TestAltLinIgniteTests2xJdk811_Cache2)
    buildType(TestAltLinIgniteTests2xJdk811_Cache1)
    buildType(TestAltLinIgniteTests2xJdk811_Cache4)
    buildType(TestAltLinIgniteTests2xJdk811_Cache3)
    buildType(TestAltLinIgniteTests2xJdk811_CacheFullApiConfigVariationsWithKeepBinary)
    buildType(TestAltLinIgniteTests2xJdk811_BinaryObjectsSimpleMapperCacheFullApi)
    buildType(TestAltLinIgniteTests2xJdk811_Cache9)
    buildType(TestAltLinIgniteTests2xJdk811_RunAllNet)
    buildType(TestAltLinIgniteTests2xJdk811_Cache6)
    buildType(TestAltLinIgniteTests2xJdk811_CacheTxRecovery)
    buildType(TestAltLinIgniteTests2xJdk811_Cache5)
    buildType(TestAltLinIgniteTests2xJdk811_Cache8)
    buildType(TestAltLinIgniteTests2xJdk811_ControlUtilityZookeeper)
    buildType(TestAltLinIgniteTests2xJdk811_Queries2lazyTrue)
    buildType(TestAltLinIgniteTests2xJdk811_Cache7)
    buildType(TestAltLinIgniteTests2xJdk811_ContinuousQuery4)
    buildType(TestAltLinIgniteTests2xJdk811_Spring)
    buildType(TestAltLinIgniteTests2xJdk811_SnapshotsWithIndexes)
    buildType(TestAltLinIgniteTests2xJdk811_CheckCodeStyleDucktests)
    buildType(TestAltLinIgniteTests2xJdk811_PlatformCCMakeWinX64Release)
    buildType(TestAltLinIgniteTests2xJdk811_CacheDeadlockDetection)
    buildType(TestAltLinIgniteTests2xJdk811_ControlUtility)
    buildType(TestAltLinIgniteTests2xJdk811_ServiceGrid)
    buildType(TestAltLinIgniteTests2xJdk811_Build)
    buildType(TestAltLinIgniteTests2xJdk811_Spi)
    buildType(TestAltLinIgniteTests2xJdk811_Kubernetes)
    buildType(TestAltLinIgniteTests2xJdk811_CacheFailoverSsl)
    buildType(TestAltLinIgniteTests2xJdk811_WebSessions)
    buildType(TestAltLinIgniteTests2xJdk811_Aop)
    buildType(TestAltLinIgniteTests2xJdk811_CassandraStore)
    buildType(TestAltLinIgniteTests2xJdk811_DiskPageCompressions1)
    buildType(TestAltLinIgniteTests2xJdk811_ThinClientPhpNew)
    buildType(TestAltLinIgniteTests2xJdk811_DevUtils)
    buildType(TestAltLinIgniteTests2xJdk811_PlatformNetCoreLinux)
    buildType(TestAltLinIgniteTests2xJdk811_DiskPageCompressions2)
    buildType(TestAltLinIgniteTests2xJdk811_RunBasicTests)
    buildType(TestAltLinIgniteTests2xJdk811_PlatformCPPCMakeLinuxClang)
    buildType(TestAltLinIgniteTests2xJdk811_Security)
    buildType(TestAltLinIgniteTests2xJdk811_ThinClientNodeJs)
    buildType(TestAltLinIgniteTests2xJdk811_CacheRestarts1)
    buildType(TestAltLinIgniteTests2xJdk811_CacheRestarts2)
    buildType(TestAltLinIgniteTests2xJdk811_ComputeAffinityRun)
    buildType(TestAltLinIgniteTests2xJdk811_StartNodes)
    buildType(TestAltLinIgniteTests2xJdk811_JdbcDriver)
    buildType(TestAltLinIgniteTests2xJdk811_DataStructures)
    buildType(TestAltLinIgniteTests2xJdk811_PdsIndexing)
    buildType(TestAltLinIgniteTests2xJdk811_Logging)
    buildType(TestAltLinIgniteTests2xJdk811_InspectionsCore)
    buildType(TestAltLinIgniteTests2xJdk811_Examples)
    buildType(TestAltLinIgniteTests2xJdk811_ComputeGrid)
    buildType(TestAltLinIgniteTests2xJdk811_Basic4)
    buildType(TestAltLinIgniteTests2xJdk811_Basic3)
    buildType(TestAltLinIgniteTests2xJdk811_Basic2)
    buildType(TestAltLinIgniteTests2xJdk811_Basic1)

    template(TestAltLinIgniteTests2xJdk811_RunIntelliJIdeaInspectionsTemplate)
    template(TestAltLinIgniteTests2xJdk811_C)
    template(TestAltLinIgniteTests2xJdk811_RunTestSuitesJavaOld)
    template(TestAltLinIgniteTests2xJdk811_PostBuild)
    template(TestAltLinIgniteTests2xJdk811_ThirdpartyCheckout)
    template(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    template(TestAltLinIgniteTests2xJdk811_RunNodeLibs)
    template(TestAltLinIgniteTests2xJdk811_PreBuild)
    template(TestAltLinIgniteTests2xJdk811_PreBuildNew)

    params {
        text("system.IGNITE_DUMP_THREADS_ON_FAILURE", "false", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("env.MALLOC_ARENA_MAX", "4", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_PROFILES", "all-java,all-scala,scala,all-other,compatibility,lgpl,yardstick,benchmarks,examples", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        param("system.SKIP_BUILD", "false")
    }

    features {
        buildTypeCustomChart {
            id = "PROJECT_EXT_10"
            title = "New chart title"
            seriesTitle = "Serie"
            format = CustomChart.Format.TEXT
            series = listOf(
                Serie(title = "Number of Failed Tests", key = SeriesKey.FAILED_TESTS)
            )
        }
        buildTypeCustomChart {
            id = "PROJECT_EXT_9"
            title = "BuildDuration"
            seriesTitle = "Serie"
            format = CustomChart.Format.TEXT
            series = listOf(
                Serie(title = "Build Duration (all stages)", key = SeriesKey.BUILD_DURATION)
            )
        }
    }

    cleanup {
        baseRule {
            all(days = 5)
            history(days = 5)
            artifacts(days = 5, artifactPatterns = """
                +:**/*
                +:.teamcity/**
            """.trimIndent())
        }
    }

    subProject(TestAltLinIgniteTests2xJdk811_Dev.Project)
})
