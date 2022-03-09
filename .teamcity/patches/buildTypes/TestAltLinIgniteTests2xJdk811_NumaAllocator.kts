package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'TestAltLinIgniteTests2xJdk811_NumaAllocator'
in the project with id = 'TestAltLinIgniteTests2xJdk811', and delete the patch script.
*/
create(RelativeId("TestAltLinIgniteTests2xJdk811"), BuildType({
    templates(RelativeId("TestAltLinIgniteTests2xJdk811_RunTestsJava"))
    id("TestAltLinIgniteTests2xJdk811_NumaAllocator")
    name = "NUMA Allocator"
    description = "NUMA Allocator Test Suite"

    params {
        text("MAVEN_MODULES", ":ignite-numa-allocator", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("TEST_SUITE", "NumaAllocatorTestSuite", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }
}))
