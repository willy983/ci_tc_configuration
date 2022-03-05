package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_ServiceGrid : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "Service Grid"

    params {
        text("JVM_ARGS", "-DIGNITE_MARSHAL_BUFFERS_RECHECK=1000", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("MAVEN_MODULES", ":ignite-schedule,:ignite-jcl,:ignite-log4j,:ignite-log4j2,:ignite-slf4j,:ignite-core", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "IgniteServiceGridTestSuite,IgniteServiceConfigVariationsFullApiTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    failureConditions {
        executionTimeoutMin = 60
    }
})
