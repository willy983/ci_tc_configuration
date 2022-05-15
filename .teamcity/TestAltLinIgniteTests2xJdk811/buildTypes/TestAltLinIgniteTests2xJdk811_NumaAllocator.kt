package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*

object TestAltLinIgniteTests2xJdk811_NumaAllocator : BuildType({
    templates(TestAltLinIgniteTests2xJdk811_RunTestsJava)
    name = "NUMA Allocator"
    description = "NUMA Allocator Test Suite"

    params {
        text("MAVEN_MODULES", ":ignite-numa-allocator", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "NumaAllocatorTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }
})
