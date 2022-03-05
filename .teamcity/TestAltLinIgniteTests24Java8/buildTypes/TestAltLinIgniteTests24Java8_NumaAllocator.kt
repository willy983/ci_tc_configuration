package TestAltLinIgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests24Java8_NumaAllocator : BuildType({
    templates(TestAltLinIgniteTests24Java8_RunTestsJava)
    name = "NUMA Allocator"
    description = "NUMA Allocator Test Suite"

    params {
        text("MAVEN_MODULES", ":ignite-numa-allocator", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "NumaAllocatorTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }
})
