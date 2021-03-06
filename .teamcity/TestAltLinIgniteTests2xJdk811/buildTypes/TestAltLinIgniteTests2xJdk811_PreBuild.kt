package TestAltLinIgniteTests2xJdk811.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

object TestAltLinIgniteTests2xJdk811_PreBuild : Template({
    name = "Pre-build"

    params {
        text("M2_REPOSITORY_DIR_WIN", """C:\.m2\repository""", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("env.JAVA_HOME", "%reverse.dep.*.env.JAVA_HOME%", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("M2_REPOSITORY_DIR_LIN", "~/.m2/repository", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        select("reverse.dep.*.env.JAVA_HOME", "%env.JDK_ORA_8%", label = "JDK version", description = "Select JDK version for all tests",
                options = listOf("JDK 8" to "%env.JDK_ORA_8%", "JDK 9" to "%env.JDK_ORA_9%", "JDK 10" to "%env.JDK_ORA_10%", "JDK 11" to "%env.JDK_OPEN_11%"))
    }

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite)

        checkoutMode = CheckoutMode.ON_SERVER
        cleanCheckout = true
    }

    steps {
        script {
            name = "Pre-build cleanup"
            id = "RUNNER_264"
            scriptContent = """
                :<<BATCH
                
                
                :: ==================== CMD =====================
                @echo on
                exit /b
                BATCH
                
                
                #  ==================== SH ======================
                set -x
                if %system.SKIP_BUILD%; then exit 0; fi
                
                # Pre-clean info
                echo "User: ${'$'}(whoami)"
                echo "JPS (before): "
                for process in ${'$'}(%env.JAVA_HOME%/bin/jps)
                do
                    echo "    ${'$'}{process}"
                done
                echo
                
                # Kill processes
                echo "Killing processes starters"
                for processName in GridHadoopExternalProcessStarter HadoopExternalProcessStarter MainWithArgsInFile
                do
                    for PID in ${'$'}(%env.JAVA_HOME%/bin/jps | grep ${'$'}{processName} | awk '{ print ${'$'}1 }')
                    do
                        echo -n "    Killing ${'$'}{processName} process with PID ${'$'}{PID}... "
                        processInfo="${'$'}(ps aux -p ${'$'}PID)"
                        kill -9 ${'$'}{PID} && echo "[OK]" || {
                            echo "[ERROR] Unable to kill process ${'$'}{PID}" && exit 1
                        }
                        echo "        Killed process info: ${'$'}{processInfo}"
                    done
                done
                echo
                echo "Killing IgniteNodeRunner processes (before tests)"
                for PID in ${'$'}(%env.JAVA_HOME%/bin/jps | grep IgniteNodeRunner | awk '{ print ${'$'}1 }')
                do
                    echo -n "    Killing process with PID ${'$'}{PID}... "
                    kill -9 ${'$'}{PID} && echo "[OK]" || {
                        echo "[ERROR] Unable to kill process ${'$'}{PID}" && exit 1
                    }
                done
                echo
                
                # Post-clean info
                echo "JPS (after): "
                for process in ${'$'}(%env.JAVA_HOME%/bin/jps)
                do
                    echo "    ${'$'}{process}"
                done
                echo
                
                # ULimit
                ulimit -n 65535 && echo "Max number of OPEN FILE DESCRIPTORS:           ${'$'}(ulimit -n)"
                ulimit -u 65535 && echo "Max number of SINGLE USER AVAILABLE PROCESSES: ${'$'}(ulimit -u)"
                echo
                
                # Finalize IPC cleaning
                echo "Cleaning IPC resources"
                for param in m s
                do
                    for ipcs in ${'$'}(ipcs -${'$'}{param} | grep 'teamcity' | awk '{ print ${'$'}2 }')
                    do
                        ipcrm -${'$'}{param} ${'$'}{ipcs} || {
                            echo "[ERROR] Unable to remove ${'$'}{param}/${'$'}{ipcs}" && exit 1
                        }
                    done
                done
            """.trimIndent()
        }
        script {
            name = "Install built artifacts to local maven repository"
            id = "RUNNER_287"
            scriptContent = """
                :<<BATCH
                
                
                :: ==================== CMD =====================
                @echo on
                del /s /f /q %M2_REPOSITORY_DIR_WIN%\org\apache\ignite
                md %M2_REPOSITORY_DIR_WIN%\org\apache\ignite
                xcopy repository\* %M2_REPOSITORY_DIR_WIN%\org\apache\ignite\ /s
                exit /b
                BATCH
                
                
                #  ==================== SH ======================
                set -x
                if %system.SKIP_BUILD%; then exit 0; fi
                
                rm -rf %M2_REPOSITORY_DIR_LIN%/org/apache/ignite
                mkdir -p %M2_REPOSITORY_DIR_LIN%/org/apache/ignite
                cp -rf repository/* %M2_REPOSITORY_DIR_LIN%/org/apache/ignite/
            """.trimIndent()
        }
    }

    dependencies {
        dependency(TestAltLinIgniteTests2xJdk811_BuildApacheIgnite) {
            snapshot {
                onDependencyFailure = FailureAction.CANCEL
                onDependencyCancel = FailureAction.CANCEL
            }

            artifacts {
                id = "ARTIFACT_DEPENDENCY_103"
                artifactRules = "ignite.zip!** => ./"
            }
        }
    }
})
