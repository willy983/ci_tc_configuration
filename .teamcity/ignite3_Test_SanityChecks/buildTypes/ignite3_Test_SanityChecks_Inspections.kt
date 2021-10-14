package ignite3_Test_SanityChecks.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.BuildFailureOnMetric
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.failOnMetricChange
import jetbrains.buildServer.configs.kotlin.v2019_2.ideaInspections
import java.io.BufferedReader
import java.io.File

fun readScript(path: String): String {
    val bufferedReader: BufferedReader = File(path).bufferedReader()
    return bufferedReader.use { it.readText() }.trimIndent()
}

object ignite3_Test_SanityChecks_Inspections : BuildType({
    name = "Inspections"
    description = "Check code with IntelliJIDEA code inspections"

    artifactRules = """
        %FILE__INSPECTIONS%
        %PATH__INSPECTION_LOGS%/** => inspections-reports-idea-logs-%build.number%.zip
    """.trimIndent()

    params {
        text("FILE__INSPECTIONS", "idea/ignite_inspections_teamcity.xml", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        text("PATH__INSPECTION_LOGS", "%system.teamcity.build.tempDir%/idea-logs", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite3)

        checkoutMode = CheckoutMode.ON_SERVER
        cleanCheckout = true
    }

    steps {
        script {
            name = "Set default inspection profile"
            scriptContent = readScript("scripts/inspect2.sh").trimIndent()
        }
        ideaInspections {
            pathToProject = "pom.xml"
            jvmArgs = """
                -Xmx4G
                -XX:ReservedCodeCacheSize=240m
                -XX:+UseG1GC
                -Didea.log.path=%PATH__INSPECTION_LOGS%/
            """.trimIndent()
            targetJdkHome = "%env.JDK_ORA_11%"
            disabledPlugins = """
                AntSupport
                CVS
                ClearcasePlugin
                Coverage
                DevKit
                Emma
                GenerateToString
                Geronimo
                Glassfish
                Guice
                HtmlTools
                IdeaServerPlugin
                Inspection-JS
                InspectionGadgets
                IntentionPowerPack
                J2ME
                Java EE: Web Services (JAX-WS)
                JBoss
                JSIntentionPowerPack
                JSR45Plugin
                JSTestDriver Plugin
                JUnit
                JavaScript
                JavaScriptDebugger
                Jetty
                NodeJS
                Osmorc
                PerforceDirectPlugin
                Pythonid
                QuirksMode
                Refactor-X
                Resin
                SourceSafe
                StrutsAssistant
                Subversion
                TFS
                TestNG-J
                Tomcat
                Type Migration
                W3Validators
                WebServicesPlugin
                WebSphere
                Weblogic
                XPathView
                XSLT-Debugger
                ZKM
                com.android.tools.idea.smali
                com.intellij.aop
                com.intellij.apacheConfig
                com.intellij.appengine
                com.intellij.aspectj
                com.intellij.beanValidation
                com.intellij.cdi
                com.intellij.commander
                com.intellij.copyright
                com.intellij.css
                com.intellij.database
                com.intellij.diagram
                com.intellij.dmserver
                com.intellij.dsm
                com.intellij.flex
                com.intellij.freemarker
                com.intellij.guice
                com.intellij.gwt
                com.intellij.hibernate
                com.intellij.java-i18n
                com.intellij.java.cucumber
                com.intellij.javaee
                com.intellij.javaee.view
                com.intellij.jsf
                com.intellij.jsp
                com.intellij.persistence
                com.intellij.phing
                com.intellij.seam
                com.intellij.seam.pageflow
                com.intellij.seam.pages
                com.intellij.spring
                com.intellij.spring.batch
                com.intellij.spring.data
                com.intellij.spring.integration
                com.intellij.spring.osgi
                com.intellij.spring.roo
                com.intellij.spring.security
                com.intellij.spring.webflow
                com.intellij.spring.ws
                com.intellij.struts2
                com.intellij.tapestry
                com.intellij.tasks
                com.intellij.tcserver
                com.intellij.uiDesigner
                com.intellij.velocity
                com.jetbrains.jarFinder
                com.jetbrains.php
                com.jetbrains.php.framework
                com.jetbrains.plugins.asp
                com.jetbrains.plugins.webDeployment
                hg4idea
                org.coffeescript
                org.intellij.grails
                org.intellij.groovy
                org.intellij.intelliLang
                org.jetbrains.android
                org.jetbrains.idea.eclipse
                org.jetbrains.idea.maven.ext
                org.jetbrains.kotlin
                org.jetbrains.plugins.django-db-config
                org.jetbrains.plugins.github
                org.jetbrains.plugins.gradle
                org.jetbrains.plugins.haml
                org.jetbrains.plugins.less
                org.jetbrains.plugins.ruby
                org.jetbrains.plugins.sass
                org.jetbrains.plugins.yaml
            """.trimIndent()
            profilePath = "%FILE__INSPECTIONS%"
        }
    }

    failureConditions {
        failOnMetricChange {
            metric = BuildFailureOnMetric.MetricType.INSPECTION_ERROR_COUNT
            threshold = 0
            units = BuildFailureOnMetric.MetricUnit.DEFAULT_UNIT
            comparison = BuildFailureOnMetric.MetricComparison.MORE
            compareTo = value()
            param("anchorBuild", "lastSuccessful")
        }
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux")
    }
})