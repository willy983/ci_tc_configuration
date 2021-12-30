package IgniteTests24Java8.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.VisualStudioStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.visualStudio

object IgniteTests24Java8_PlatformCWinX64Release : BuildType({
    templates(IgniteTests24Java8_RunTestSuitesJavaOld, IgniteTests24Java8_C)
    name = "~[DEPRECATED] Platform C++ (Win x64 | Release)"
    paused = true

    params {
        param("SCALA_PROFILE", "-scala")
        param("env.CPP_DIR", """%env.IGNITE_HOME%\modules\platforms\cpp""")
        param("env.BOOST_LIB32_PATH", """%env.BOOST_HOME%\lib32-msvc-14.1""")
        param("env.BOOST_LIB_SUFIX_D32", "vc141-mt-gd-x32-1_77")
        param("env.ODBCDIR", """C:\odbc\amd64""")
        param("env.BOOST_LIB_SUFIX_R64", "vc141-mt-x64-1_77")
        param("env.BOOST_LIB_SUFIX_D64", "vc141-mt-gd-x64-1_77")
        param("IGNITE_ODBC_PROFILE", "ignite-odbc-amd64")
        param("env.BOOST_LIB64_PATH", """%env.BOOST_HOME%\lib64-msvc-14.1""")
        param("env.BOOST_LIB_SUFIX_R32", "vc141-mt-x32-1_77")
    }

    steps {
        script {
            name = "Add AdditionalDependencies for build VS 2017"
            id = "RUNNER_102"
            workingDir = "%env.CPP_DIR%/odbc"
            scriptContent = """
                @echo on
                
                bash -c " sed -i -r 's/(\x3CAdditionalDependencies\x3E)(.*)(\x3C\x2FAdditionalDependencies\x3E)/\1legacy_stdio_definitions.lib;\2\3/' project/vs/odbc.vcxproj; grep -e "AdditionalDependencies" project/vs/odbc.vcxproj"
            """.trimIndent()
        }
        visualStudio {
            name = "Build C++"
            id = "RUNNER_83"
            path = "modules/platforms/cpp/project/vs/ignite.sln"
            version = VisualStudioStep.VisualStudioVersion.vs2017
            runPlatform = VisualStudioStep.Platform.x86
            msBuildVersion = VisualStudioStep.MSBuildVersion.V15_0
            msBuildToolsVersion = VisualStudioStep.MSBuildToolsVersion.V15_0
            targets = "Clean;Rebuild"
            configuration = "Release"
            platform = "x64"
            args = """
                /p:PlatformToolset=Windows7.1SDK
                /p:PlatformToolset=v141
                /p:ShouldUnsetParentConfigurationAndPlatform=false
            """.trimIndent()
        }
        visualStudio {
            name = "Build C++ Examples"
            id = "RUNNER_150"
            path = "modules/platforms/cpp/examples/project/vs/ignite-examples.sln"
            version = VisualStudioStep.VisualStudioVersion.vs2017
            runPlatform = VisualStudioStep.Platform.x86
            msBuildVersion = VisualStudioStep.MSBuildVersion.V15_0
            msBuildToolsVersion = VisualStudioStep.MSBuildToolsVersion.V15_0
            targets = "Clean;Rebuild"
            configuration = "Release"
            platform = "x64"
            args = """
                /p:PlatformToolset=Windows7.1SDK 
                /p:PlatformToolset=v141
                /p:ShouldUnsetParentConfigurationAndPlatform=false
            """.trimIndent()
        }
        script {
            name = "Uninstall ODBC driver"
            id = "RUNNER_74"
            scriptContent = """
                @echo on
                chcp 65001
                wmic product where 'Vendor like "%%%%Apache%%%%"' call uninstall || exit 0
            """.trimIndent()
        }
        script {
            name = "Create MSI installer for ODBC driver"
            id = "RUNNER_69"
            workingDir = """%env.CPP_DIR%\odbc\install"""
            scriptContent = """
                @echo on
                
                candle.exe %IGNITE_ODBC_PROFILE%.wxs || exit 1
                light.exe -ext WixUIExtension %IGNITE_ODBC_PROFILE%.wixobj || exit 1
            """.trimIndent()
        }
        script {
            name = "Install ODBC driver"
            id = "RUNNER_70"
            workingDir = """%env.CPP_DIR%\odbc\install"""
            scriptContent = """
                @echo on
                
                msiexec.exe /i %IGNITE_ODBC_PROFILE%.msi /qn
            """.trimIndent()
        }
        script {
            name = "Run Core tests"
            id = "RUNNER_151"
            workingDir = """modules\platforms\cpp\project\vs\x64\Release"""
            scriptContent = """
                @echo on
                
                core-test.exe || echo "Some tests failed"
            """.trimIndent()
        }
        script {
            name = "Run ODBC tests"
            id = "RUNNER_152"
            workingDir = """modules\platforms\cpp\project\vs\x64\Release"""
            scriptContent = """
                @echo on
                
                echo "Running ODBC tests"
                odbc-test.exe || echo "Some tests failed"
            """.trimIndent()
        }
        script {
            name = "Run Thin Client tests"
            id = "RUNNER_194"
            workingDir = """modules\platforms\cpp\project\vs\x64\Release"""
            scriptContent = """
                @echo on
                
                IF EXIST thin-client-test.exe (
                    thin-client-test.exe || echo "Some tests failed"
                )
            """.trimIndent()
        }
        script {
            name = "Move examples"
            id = "RUNNER_153"
            enabled = false
            scriptContent = """
                if not exist platforms\ (
                  echo "Creating platforms folder"
                  md platforms
                )
                
                if not exist platforms\cpp (
                  echo "Creating platforms\cpp folder"
                  md platforms\cpp
                )
                
                xcopy modules\platforms\cpp\examples platforms\cpp\examples\ /E
            """.trimIndent()
        }
        stepsOrder = arrayListOf("RUNNER_264", "RUNNER_287", "RUNNER_102", "RUNNER_83", "RUNNER_150", "RUNNER_74", "RUNNER_69", "RUNNER_70", "RUNNER_151", "RUNNER_152", "RUNNER_194", "RUNNER_153", "RUNNER_225", "RUNNER_265", "RUNNER_266")
    }

    failureConditions {
        executionTimeoutMin = 60
    }

    requirements {
        exists("DotNetFramework4.0_x86", "RQ_14")
        contains("teamcity.agent.jvm.os.name", "Windows 10", "RQ_15")
    }
    
    disableSettings("RQ_10", "RQ_14", "RUNNER_265")
})
