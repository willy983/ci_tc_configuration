package Releases_ApacheIgniteMain.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.MavenBuildStep
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*


object ApacheIgniteReleaseJava8_TempIgniteRelease5GenerateReleaseReports : BuildType({
    name = "~[Obsolete] [IGNITE_RELEASE #5] Generate Release Reports"

    artifactRules = """
        modules/tools/target/*.html
        modules/tools/target/*.json
        modules/tools/target/*.css
    """.trimIndent()

    params {
        text("Release Version", "", allowEmpty = false)
        text("Release Description", "", allowEmpty = true)
    }

    vcs {
        root(RelativeId("GitHubApacheIgnite"))

        cleanCheckout = true
    }

    steps {
        maven {
            name = "Build Ignite Tools"
            goals = "clean package"
            pomLocation = "modules/tools/pom.xml"
            localRepoScope = MavenBuildStep.RepositoryScope.MAVEN_DEFAULT
        }
        script {
            name = "Create Report Template File"
            scriptContent = """
                template=${'$'}(cat <<EOF
                {
                  "header": "Apache IGNITE %Release Version%",
                  "description": "%Release Description%",
                  "outfile": "apache-ignite-%Release Version%.html",
                  "servers": [
                    {
                      "id": 1,
                      "apiurl": "https://issues.apache.org/jira/rest/api/2/",
                      "baseUrl": "https://issues.apache.org/jira/browse/",
                      "username": "",
                      "password": ""
                    }
                  ],
                  "items": [
                    {
                      "header": "Features and Improvements",
                      "search": [
                        { "server": 1,
                          "showlink": true,
                          "jql": "project in (\"IGNITE\") and fixVersion = %Release Version% and type in (\"New Feature\") and (component != documentation or component is EMPTY) and ((labels != .net and labels != .NET and labels != cpp) or labels is EMPTY) and status in (Closed, Resolved)"
                        },
                        { "server": 1,
                          "showlink": true,
                          "jql": "project in (\"IGNITE\") and fixVersion = %Release Version% and type in (Task) and (component != documentation or component is EMPTY) and ((labels != .net and labels != .NET and labels != cpp) or labels is EMPTY) and status in (Closed, Resolved)"
                        },
                        { "server": 1,
                          "showlink": true,
                          "jql": "project in (\"IGNITE\") and fixVersion = %Release Version% and type in (Improvement) and (component != documentation or component is EMPTY) and ((labels != .net and labels != .NET and labels != cpp) or labels is EMPTY) and status in (Closed, Resolved)"
                        }
                      ]
                    },
                    {
                      "header": "Fixed",
                      "search": [
                        { "server": 1,
                          "showlink": true,
                          "jql": "project in (\"IGNITE\") and fixVersion = %Release Version% and affectedVersion != %Release Version%  and type in (Bug) and (component != documentation or component is EMPTY) and ((labels != .net and labels != .NET  and labels != cpp) or labels is EMPTY) and status in (Closed, Resolved)"
                        }
                      ]
                    },
                    {
                      "header": ".NET: Features and Improvements",
                      "search": [
                        { "server": 1,
                          "showlink": true,
                          "jql": "project in (\"IGNITE\") and fixVersion = %Release Version% and type in (\"New Feature\") and (component != documentation or component is EMPTY) and (labels = .net or labels = .NET) and status in (Closed, Resolved)"
                        },
                        { "server": 1,
                          "showlink": true,
                          "jql": "project in (\"IGNITE\") and fixVersion = %Release Version% and type in (Task) and (component != documentation or component is EMPTY) and (labels = .net or labels = .NET) and status in (Closed, Resolved)"
                        },
                        { "server": 1,
                          "showlink": true,
                          "jql": "project in (\"IGNITE\") and fixVersion = %Release Version% and type in (Improvement) and (component != documentation or component is EMPTY) and (labels = .net or labels = .NET) and status in (Closed, Resolved)"
                        }
                      ]
                    },
                    {
                      "header": ".NET: Fixed",
                      "search": [
                        { "server": 1,
                          "showlink": true,
                          "jql": "project in (\"IGNITE\") and fixVersion = %Release Version% and affectedVersion != %Release Version%  and type in (Bug) and (component != documentation or component is EMPTY) and (labels = .net or labels = .NET) and status in (Closed, Resolved)"
                        }
                      ]
                    },
                    {
                      "header": "C++: Features and Improvements",
                      "search": [
                        { "server": 1,
                          "showlink": true,
                          "jql": "project in (\"IGNITE\") and fixVersion = %Release Version% and type in (\"New Feature\") and (component != documentation or component is EMPTY) and (labels = cpp) and status in (Closed, Resolved)"
                        },
                        { "server": 1,
                          "showlink": true,
                          "jql": "project in (\"IGNITE\") and fixVersion = %Release Version% and type in (Task) and (component != documentation or component is EMPTY) and (labels = cpp) and status in (Closed, Resolved)"
                        },
                        { "server": 1,
                          "showlink": true,
                          "jql": "project in (\"IGNITE\") and fixVersion = %Release Version% and type in (Improvement) and (component != documentation or component is EMPTY) and (labels = cpp) and status in (Closed, Resolved)"
                        }
                      ]
                    },
                    {
                      "header": "C++: Fixed",
                      "search": [
                        { "server": 1,
                          "showlink": true,
                          "jql": "project in (\"IGNITE\") and fixVersion = %Release Version% and affectedVersion != %Release Version%  and type in (Bug) and (component != documentation or component is EMPTY) and (labels = cpp) and status in (Closed, Resolved)"
                        }
                      ]
                    }
                  ]
                }
                EOF
                )
                
                echo ${'$'}template > modules/tools/target/report_template.json
            """.trimIndent()
        }
        script {
            name = "Create Report CSS File"
            scriptContent = """
                css=${'$'}(cat <<EOF
                h1 {
                  color: #113847;
                  font-size: 33px;
                  font-weight: bold;
                  margin: 30px 0 15px 0;
                  padding-bottom: 7px;
                  width: 700px;
                }h2 {  border-bottom: 2px solid #ccc;
                  color: #113847;
                  font-size: 29px;
                  font-weight: normal;
                  margin: 30px 0 15px 0;
                  padding-bottom: 7px;  width: 700px;
                }a {
                  color: #cc0000;
                  text-decoration: none;
                }
                span {
                  color: #cc0000;
                }
                a:hover {
                  text-decoration: underline;
                }
                ul,
                ol {
                  list-style: disc;
                  margin-left: 30px;
                }
                
                ul li,
                ol li {
                  margin: 5px 0;
                }
                
                p.description {
                  width: 700px;
                }
                
                EOF
                )
                
                echo ${'$'}css > modules/tools/target/report_template.css
            """.trimIndent()
        }
        script {
            name = "Build HTML Release Report"
            scriptContent = """
                cd modules/tools/target
                ls -la
                java -cp "./*:libs/*" org.apache.ignite.tools.release.ReleaseReportGenerator
            """.trimIndent()
        }
    }

    requirements {
        doesNotEqual("env.OS", "Windows_NT")
    }
})