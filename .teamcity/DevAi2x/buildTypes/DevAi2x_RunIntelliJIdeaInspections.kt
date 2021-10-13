package DevAi2x.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.BuildFailureOnMetric
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.failOnMetricChange
import jetbrains.buildServer.configs.kotlin.v2019_2.ideaInspections

object DevAi2x_RunIntelliJIdeaInspections : Template({
    name = "Run IntelliJ IDEA Inspections"

    artifactRules = """
        idea/ignite_inspections_teamcity.xml
        %system.teamcity.build.tempDir%/idea-logs/** => inspections-reports-idea-logs-%build.number%.zip
    """.trimIndent()

    params {
        param("env.JAVA_HOME", "%env.JDK_ORA_8%")
        param("MODULE_PATH", "")
        param("system.teamcity.dont.delete.temp.result.dir", "true")
    }

    vcs {
        root(_Self.vcsRoots.GitHubApacheIgnite)

        cleanCheckout = true
    }

    steps {
        script {
            name = "Set default inspection profile"
            id = "RUNNER_59"
            scriptContent = """
                #!/usr/bin/env bash
                set -x
                
                
                ## Set default inspection profile in case of absence
                ls -l idea
                if [ ! -f idea/ignite_inspections_teamcity.xml ]; then
                    cat<<EOF>idea/ignite_inspections_teamcity.xml
                <profile version="1.0">
                  <option name="myName" value="ignite_inspections_teamcity" />
                  <inspection_tool class="SizeReplaceableByIsEmpty" enabled="false" level="WARNING" enabled_by_default="true" />
                  <inspection_tool class="UnusedImport" enabled="false" level="WARNING" enabled_by_default="true" />
                  <inspection_tool class="MissingOverrideAnnotation" enabled="false" level="WARNING" enabled_by_default="true">
                    <option name="ignoreObjectMethods" value="true" />
                    <option name="ignoreAnonymousClassMethods" value="false" />
                  </inspection_tool>
                  <inspection_tool class="MissortedModifiers" enabled="false" level="WARNING" enabled_by_default="true">
                    <option name="m_requireAnnotationsFirst" value="true" />
                  </inspection_tool>
                  <inspection_tool class="AccessStaticViaInstance" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AccessorLikeMethodIsEmptyParen" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AccessorLikeMethodIsUnit" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AddVarianceModifier" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="AmmoniteUnresolvedLibraryInspection" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidDomInspection" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidElementNotAllowed" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintAddJavascriptInterface" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintAllowAllHostnameVerifier" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintAlwaysShowAction" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintAppCompatMethod" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintAuthLeak" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintBadHostnameVerifier" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintBatteryLife" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintCommitPrefEdits" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintCommitTransaction" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintCustomViewStyleable" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintCutPasteId" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintDefaultLocale" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintDrawAllocation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintExportedContentProvider" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintExportedPreferenceActivity" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintExportedReceiver" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintExportedService" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintFloatMath" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintGetInstance" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintGifUsage" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintGoogleAppIndexingUrlError" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintGoogleAppIndexingWarning" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintGrantAllUris" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintHandlerLeak" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintIconColors" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintIconDensities" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintIconDipSize" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintIconDuplicates" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintIconDuplicatesConfig" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintIconExtension" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintIconLauncherShape" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintIconLocation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintIconMissingDensityFolder" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintIconMixedNinePatch" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintIconNoDpi" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintIconXmlAndPng" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintInconsistentLayout" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintInflateParams" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintInlinedApi" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintInvalidUsesTagAttribute" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintJavascriptInterface" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintLocalSuppress" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintLogTagMismatch" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintLongLogTag" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintMergeRootFrame" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintMissingIntentFilterForMediaSearch" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintMissingMediaBrowserServiceIntentFilter" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintMissingOnPlayFromSearch" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintMissingSuperCall" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintNewApi" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintOverdraw" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintOverride" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintOverrideAbstract" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintPackageManagerGetSignatures" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintParcelClassLoader" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintParcelCreator" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintPendingBindings" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintPluralsCandidate" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintPrivateResource" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintRecycle" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintRecyclerView" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintRegistered" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintRequiredSize" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintRtlCompat" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintRtlEnabled" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintRtlHardcoded" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintRtlSymmetry" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintSQLiteString" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintSSLCertificateSocketFactoryCreateSocket" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintSSLCertificateSocketFactoryGetInsecure" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintSdCardPath" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintSecureRandom" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintServiceCast" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintSetJavaScriptEnabled" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintSetTextI18n" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintSetWorldReadable" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintSetWorldWritable" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintShiftFlags" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintShortAlarm" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintShowToast" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintSimpleDateFormat" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintStringFormatCount" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintStringFormatInvalid" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintStringFormatMatches" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintSupportAnnotationUsage" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintSuspiciousImport" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintSwitchIntDef" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintTrustAllX509TrustManager" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintUniqueConstants" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintUnlocalizedSms" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintUnprotectedSMSBroadcastReceiver" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintUnsafeDynamicallyLoadedCode" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintUnsafeNativeCodeLocation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintUnsafeProtectedBroadcastReceiver" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintUnusedAttribute" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintUseSparseArrays" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintUseValueOf" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintValidFragment" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintViewConstructor" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintViewHolder" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintViewTag" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintWorldReadableFiles" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintWorldWriteableFiles" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintWrongCall" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidKLintWrongViewCast" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintAaptCrash" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintAccidentalOctal" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintAdapterViewChildren" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintAddJavascriptInterface" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintAllCaps" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintAllowAllHostnameVerifier" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintAllowBackup" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintAlwaysShowAction" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintAnimatorKeep" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintAppCompatCustomView" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintAppCompatMethod" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintAppCompatResource" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintAppLinkUrlError" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintApplySharedPref" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintAssert" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintAuthLeak" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintBadHostnameVerifier" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintBatteryLife" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintButtonCase" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintButtonOrder" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintButtonStyle" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintByteOrderMark" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintCheckResult" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintClickableViewAccessibility" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintCommitPrefEdits" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintCommitTransaction" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintContentDescription" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintCustomViewStyleable" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintCutPasteId" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintDefaultLocale" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintDeprecated" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintDevModeObsolete" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintDeviceAdmin" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintDisableBaselineAlignment" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintDrawAllocation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintDuplicateActivity" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintDuplicateDefinition" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintDuplicateDivider" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintDuplicateIds" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintDuplicateIncludedIds" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintDuplicatePlatformClasses" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintDuplicateUsesFeature" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintEllipsizeMaxLines" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintEnforceUTF8" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintExifInterface" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintExportedContentProvider" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintExportedPreferenceActivity" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintExportedReceiver" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintExportedService" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintExtraText" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintExtraTranslation" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintFindViewByIdCast" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintFloatMath" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintFontValidationError" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintFontValidationWarning" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintFullBackupContent" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintGetContentDescriptionOverride" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintGetInstance" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintGetLocales" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintGifUsage" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintGoogleAppIndexingUrlError" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintGoogleAppIndexingWarning" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintGradleCompatible" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintGradleDependency" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintGradleDeprecated" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintGradleDynamicVersion" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintGradleGetter" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintGradleIdeError" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintGradleOverrides" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintGradlePath" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintGradlePluginVersion" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintGrantAllUris" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintGridLayout" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintHandlerLeak" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintHardcodedDebugMode" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintHardcodedText" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintHardwareIds" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintHighAppVersionCode" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintIconColors" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintIconDensities" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintIconDipSize" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintIconDuplicates" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintIconDuplicatesConfig" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintIconExtension" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintIconLauncherShape" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintIconLocation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintIconMissingDensityFolder" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintIconMixedNinePatch" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintIconNoDpi" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintIconXmlAndPng" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintIllegalResourceRef" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintImpliedQuantity" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintImpliedTouchscreenHardware" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintInOrMmUsage" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintIncludeLayoutParam" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintIncompatibleMediaBrowserServiceCompatVersion" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintInconsistentArrays" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintInconsistentLayout" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintInefficientWeight" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintInflateParams" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintInlinedApi" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintInnerclassSeparator" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintInstantApps" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintInvalidAnalyticsName" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintInvalidId" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintInvalidImeActionId" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintInvalidPermission" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintInvalidResourceFolder" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintInvalidUsesTagAttribute" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintInvalidVectorPath" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintInvalidWearFeatureAttribute" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintJavascriptInterface" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintJobSchedulerService" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintKeyboardInaccessibleWidget" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintLabelFor" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintLibraryCustomView" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintLintBaseline" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintLocalSuppress" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintLocaleFolder" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintLogTagMismatch" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintLongLogTag" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintManifestOrder" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintManifestResource" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMenuTitle" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMergeMarker" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMergeRootFrame" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMinSdkTooLow" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMipmapIcons" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMissingApplicationIcon" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMissingBackupPin" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMissingConstraints" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMissingFirebaseInstanceTokenRefresh" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMissingId" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMissingIntentFilterForMediaSearch" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMissingLeanbackLauncher" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMissingLeanbackSupport" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMissingMediaBrowserServiceIntentFilter" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMissingOnPlayFromSearch" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMissingPermission" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMissingPrefix" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMissingQuantity" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMissingSuperCall" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMissingTranslation" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMissingTvBanner" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMissingVersion" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMockLocation" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintMultipleUsesSdk" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintNamespaceTypo" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintNestedScrolling" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintNestedWeights" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintNetworkSecurityConfig" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintNewApi" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintNfcTechWhitespace" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintNotInterpolated" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintNotSibling" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintObjectAnimatorBinding" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintObsoleteLayoutParam" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintObsoleteSdkInt" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintOldTargetApi" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintOnClick" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintOrientation" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintOverdraw" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintOverride" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintOverrideAbstract" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintPackageManagerGetSignatures" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintPackagedPrivateKey" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintParcelClassLoader" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintParcelCreator" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintPendingBindings" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintPermissionImpliesUnsupportedHardware" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintPinSetExpiry" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintPluralsCandidate" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintPrivateApi" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintPrivateResource" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintProguard" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintProguardSplit" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintPropertyEscape" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintProtectedPermissions" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintPxUsage" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintRange" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintRecycle" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintRecyclerView" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintReferenceType" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintRegistered" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintRelativeOverlap" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintRequiredSize" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintResAuto" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintResourceAsColor" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintResourceCycle" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintResourceName" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintResourceType" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintRestrictedApi" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintRtlCompat" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintRtlEnabled" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintRtlHardcoded" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintRtlSymmetry" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintSQLiteString" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintSSLCertificateSocketFactoryCreateSocket" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintSSLCertificateSocketFactoryGetInsecure" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintScrollViewCount" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintScrollViewSize" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintSdCardPath" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintSecureRandom" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintServiceCast" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintSetJavaScriptEnabled" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintSetTextI18n" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintSetWorldReadable" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintSetWorldWritable" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintShiftFlags" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintShortAlarm" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintShowToast" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintSignatureOrSystemPermissions" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintSimpleDateFormat" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintSmallSp" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintSpUsage" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintStateListReachable" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintStaticFieldLeak" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintStringEscaping" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintStringFormatCount" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintStringFormatInvalid" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintStringFormatMatches" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintStringShouldBeInt" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintSupportAnnotationUsage" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintSuspicious0dp" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintSuspiciousImport" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintSwitchIntDef" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintTestAppLink" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintTextFields" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintTextViewEdits" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintTooDeepLayout" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintTooManyViews" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintTrustAllX509TrustManager" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintTypographyDashes" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintTypographyEllipsis" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintTypographyFractions" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintTypographyOther" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintTypos" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUniqueConstants" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUniquePermission" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUnknownId" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUnknownIdInLayout" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUnlocalizedSms" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUnpackedNativeCode" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUnprotectedSMSBroadcastReceiver" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUnsafeDynamicallyLoadedCode" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUnsafeNativeCodeLocation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUnsafeProtectedBroadcastReceiver" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUnsupportedTvHardware" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUnusedAttribute" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUnusedQuantity" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUnusedResources" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUseAlpha2" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUseCheckPermission" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUseCompoundDrawables" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUseOfBundledGooglePlayServices" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUseSparseArrays" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUseValueOf" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUselessLeaf" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUselessParent" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUsesMinSdkAttributes" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintUsingHttp" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintValidFragment" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintValidRestrictions" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintVectorDrawableCompat" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintVectorPath" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintVectorRaster" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintViewConstructor" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintViewHolder" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintViewTag" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintVisibleForTests" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintWakelockTimeout" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintWearStandaloneAppFlag" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintWearableBindListener" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintWebViewLayout" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintWebpUnsupported" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintWifiManagerLeak" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintWifiManagerPotentialLeak" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintWorldReadableFiles" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintWorldWriteableFiles" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintWrongCall" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintWrongCase" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintWrongConstant" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintWrongFolder" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintWrongRegion" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintWrongThread" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidLintWrongViewCast" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidMissingOnClickHandler" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AndroidNonConstantResIdsInSwitch" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AndroidUnknownAttribute" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Annotator" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="Anonymous2MethodRef" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AnonymousHasLambdaAlternative" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AntDuplicateTargetsInspection" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AntMissingPropertiesFileInspection" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="AntResolveInspection" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="ApparentRefinementOfResultType" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AppliedTypeLambdaCanBeSimplified" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ArrayCreationWithoutNewKeyword" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="ArrayEquals" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ArrayHashCode" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ArrayInDataClass" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="ArrayObjectsEquals" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ArraysAsListWithZeroOrOneArgument" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AssertEqualsBetweenInconvertibleTypes" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AssertEqualsBetweenInconvertibleTypesTestNG" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AssertWithSideEffects" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AssertionCanBeIf" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="AssignmentOrReturnOfFieldWithMutableTypeMerged" />
                  <inspection_tool class="AssignmentToDateFieldFromParameter" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="ignorePrivateMethods" value="true" />
                  </inspection_tool>
                  <inspection_tool class="AtomicFieldUpdaterIssues" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="AtomicFieldUpdaterNotStaticFinal" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="BigDecimalMethodWithoutRoundingCalled" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="BooleanConstructor" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="BooleanMethodIsAlwaysInverted" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="BoundFieldAssignment" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="BoxingBoxedValue" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CStyleArrayDeclaration" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="CachedNumberConstructorCall" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CanBeFinal" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="REPORT_CLASSES" value="false" />
                    <option name="REPORT_METHODS" value="false" />
                    <option name="REPORT_FIELDS" value="true" />
                  </inspection_tool>
                  <inspection_tool class="CanBeParameter" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CanBePrimaryConstructorProperty" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CanBeVal" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CapturingCleaner" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CascadeIf" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="CaseClassParam" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CatchMayIgnoreException" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="m_ignoreCatchBlocksWithComments" value="false" />
                  </inspection_tool>
                  <inspection_tool class="CaughtExceptionImmediatelyRethrown" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ChainedPackage" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ChangeToMethod" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="ChangeToOperator" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="CheckDtdRefs" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="CheckEmptyScriptTag" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CheckNodeTest" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CheckTagEmptyBody" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CheckValidXmlInScriptTagBody" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="CheckXmlFileWithXercesValidator" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="ClashingTraitMethods" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ClassEscapesItsScope" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ClassInitializerMayBeStatic" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ClassMayBeInterface" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="ClassName" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="CloneDeclaresCloneNotSupported" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CloneableImplementsClone" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="m_ignoreCloneableDueToInheritance" value="true" />
                  </inspection_tool>
                  <inspection_tool class="CodeBlock2Expr" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CollectHeadOption" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CollectionAddAllCanBeReplacedWithConstructor" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CollectionAddedToSelf" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CommentAbsent" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ComparatorCombinators" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ComparatorMethodParameterNotUsed" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ComparatorResultComparison" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ComparingDiffCollectionKinds" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ComparingUnrelatedTypes" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ComponentNotRegistered" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="CHECK_ACTIONS" value="true" />
                    <option name="IGNORE_NON_PUBLIC" value="true" />
                  </inspection_tool>
                  <inspection_tool class="ComponentRegistrationProblems" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="ConditionalBreakInInfiniteLoop" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ConditionalCanBeOptional" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="ConditionalCanBePushedInsideExpression" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="ConditionalExpression" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="ConflictingExtensionProperty" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ConfusingElse" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="ConstPropertyName" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="ConstantConditionIf" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="ConstantConditionalExpression" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ConstantConditions" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="SUGGEST_NULLABLE_ANNOTATIONS" value="true" />
                    <option name="DONT_REPORT_TRUE_ASSERT_STATEMENTS" value="true" />
                  </inspection_tool>
                  <inspection_tool class="ConstantExpression" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="ContinueOrBreakFromFinallyBlock" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Contract" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ControlFlowStatementWithoutBraces" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="Convert2Diamond" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Convert2Lambda" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Convert2MethodRef" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Convert2streamapi" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="ConvertExpressionToSAM" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ConvertLambdaToReference" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="ConvertNullInitializerToUnderscore" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ConvertReferenceToLambda" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="ConvertSecondaryConstructorToPrimary" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ConvertToStringTemplate" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="ConvertTryFinallyToUseCall" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="ConvertTwoComparisonsToRangeCheck" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="ConvertibleToMethodValue" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CopyConstructorMissesField" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CopyWithoutNamedArguments" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="CorrespondsUnsorted" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="CovariantCompareTo" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DangerousCatchAll" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DanglingJavadoc" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DataClassPrivateConstructor" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DataProviderReturnType" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="DefaultAnnotationParam" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DefaultFileTemplate" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="CHECK_FILE_HEADER" value="true" />
                    <option name="CHECK_TRY_CATCH_SECTION" value="true" />
                    <option name="CHECK_METHOD_BODY" value="true" />
                  </inspection_tool>
                  <inspection_tool class="DelegatesTo" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Dependency" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="DeprecatedCallableAddReplaceWith" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="DeprecatedClassUsageInspection" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DeprecatedGradleDependency" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DeprecatedIsStillUsed" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DeprecatedMavenDependency" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DeprecatedViewBound" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Deprecation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DestructuringWrongName" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DialogTitleCapitalization" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DiamondCanBeReplacedWithExplicitTypeArguments" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="DifferentKotlinGradleVersion" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DifferentKotlinMavenVersion" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DifferentMavenStdlibVersion" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DifferentStdlibGradleVersion" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DivideByZero" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DontUsePairConstructor" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DottyDeprecatedWith" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DoubleNegation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DoubleNegationScala" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DropTakeToSlice" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DuplicateMnemonic" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DuplicateThrows" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="DuplicatedDataProviderNames" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="EmptyCheck" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="EmptyFinallyBlock" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="EmptyMethod" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="EmptyParenMethodAccessedAsParameterless" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="EmptyParenMethodOverriddenAsParameterless" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="EmptyParenMethodOverridenAsParameterless" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="EmptyRange" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="EmptyStatementBody" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="m_reportEmptyBlocks" value="true" />
                  </inspection_tool>
                  <inspection_tool class="EmptyTryBlock" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="EndlessStream" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="EnumEntryName" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="EnumSwitchStatementWhichMissesCases" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="ignoreSwitchStatementsWithDefault" value="true" />
                  </inspection_tool>
                  <inspection_tool class="EqualityToSameElements" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="EqualsBetweenInconvertibleTypes" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="EqualsOnSuspiciousObject" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="EqualsOrHashCode" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="EqualsReplaceableByObjectsCall" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="EqualsWhichDoesntCheckParameterClass" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="EqualsWithItself" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ExcessiveLambdaUsage" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ExistsEquals" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ExistsForallReplace" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ExplicitArgumentCanBeLambda" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="ExplicitThis" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="ExtendsAnnotation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ExtendsObject" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ExternalizableWithoutPublicNoArgConstructor" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FakeJvmFieldConstant" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FieldCanBeLocal" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FieldFromDelayedInit" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FileEqualsUsage" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FilterEmptyCheck" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FilterHeadOption" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FilterOtherContains" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FilterSize" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FinalPrivateMethod" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FinalStaticMethod" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FinallyBlockCannotCompleteNormally" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FindAndMapToApply" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FindEmptyCheck" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FloatLiteralEndingWithDecimalPoint" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FoldInitializerAndIfToElvis" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="FoldTrueAnd" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ForCanBeForeach" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="REPORT_INDEXED_LOOP" value="true" />
                    <option name="ignoreUntypedCollections" value="false" />
                  </inspection_tool>
                  <inspection_tool class="ForLoopReplaceableByWhile" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="FormSpellChecking" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ForwardReference" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FrequentlyUsedInheritorInspection" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="FunctionName" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="FunctionTupleSyntacticSugar" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FunctionalExpressionCanBeFolded" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="FuseStreamOperations" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GetGetOrElse" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GetOrElseNull" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GrDeprecatedAPIUsage" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GrEqualsBetweenInconvertibleTypes" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GrFinalVariableAccess" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GrMethodMayBeStatic" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GrPackage" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GrReassignedInClosureLocalVar" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GrUnnecessaryAlias" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="GrUnnecessaryDefModifier" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GrUnnecessaryPublicModifier" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GrUnnecessarySemicolon" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GrUnresolvedAccess" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyAccessToStaticFieldLockedOnInstance" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyAccessibility" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyAssignabilityCheck" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyConditionalWithIdenticalBranches" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyConstantConditional" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyConstantIfStatement" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyConstructorNamedArguments" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyDivideByZero" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyDocCheck" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="GroovyDoubleNegation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyDuplicateSwitchBranch" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyEmptyStatementBody" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyFallthrough" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyGStringKey" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyIfStatementWithIdenticalBranches" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyInArgumentCheck" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyInfiniteLoopStatement" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyInfiniteRecursion" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyLabeledStatement" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyMissingReturnStatement" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyPointlessBoolean" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyResultOfObjectAllocationIgnored" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovySillyAssignment" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovySynchronizationOnNonFinalField" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovySynchronizationOnVariableInitializedWithLiteral" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyTrivialConditional" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyTrivialIf" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyUncheckedAssignmentOfMemberOfRawType" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyUnnecessaryContinue" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyUnnecessaryReturn" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyUnreachableStatement" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyUnsynchronizedMethodOverridesSynchronizedMethod" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyUnusedAssignment" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyUnusedCatchParameter" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyUnusedDeclaration" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyUnusedIncOrDec" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GroovyVariableNotAssigned" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="GtkPreferredJComboBoxRenderer" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Guava" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="HardwiredNamespacePrefix" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="HasPlatformType" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="HashCodeUsesVar" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="HeadOrLastOption" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="HoconIncludeResolution" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="HtmlExtraClosingTag" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="HtmlMissingClosingTag" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="HtmlPresentationalElement" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="HtmlUnknownAnchorTarget" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="HtmlUnknownAttribute" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="myValues">
                      <value>
                        <list size="0" />
                      </value>
                    </option>
                    <option name="myCustomValuesEnabled" value="true" />
                  </inspection_tool>
                  <inspection_tool class="HtmlUnknownBooleanAttribute" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="HtmlUnknownTag" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="myValues">
                      <value>
                        <list size="6">
                          <item index="0" class="java.lang.String" itemvalue="nobr" />
                          <item index="1" class="java.lang.String" itemvalue="noembed" />
                          <item index="2" class="java.lang.String" itemvalue="comment" />
                          <item index="3" class="java.lang.String" itemvalue="noscript" />
                          <item index="4" class="java.lang.String" itemvalue="embed" />
                          <item index="5" class="java.lang.String" itemvalue="script" />
                        </list>
                      </value>
                    </option>
                    <option name="myCustomValuesEnabled" value="true" />
                  </inspection_tool>
                  <inspection_tool class="HtmlUnknownTarget" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="I18nForm" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="IdempotentLoopBody" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="IfCanBeAssertion" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="IfCanBeSwitch" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="minimumBranches" value="3" />
                    <option name="suggestIntSwitches" value="false" />
                    <option name="suggestEnumSwitches" value="false" />
                  </inspection_tool>
                  <inspection_tool class="IfElseToFilterdOption" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="IfElseToOption" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="IfThenToElvis" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="IfThenToSafeAccess" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="IgniteAnnotation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="IgniteBracket" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="IgniteEmptyLine" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="IgnitePlublicInterfaceMethods" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="IgniteWrongComparation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="IgnoreResultOfCall" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="m_reportAllNonLibraryCalls" value="false" />
                    <option name="callCheckString" value="java.io.InputStream,read,java.io.InputStream,skip,java.lang.StringBuffer,toString,java.lang.StringBuilder,toString,java.lang.String,.*,java.math.BigInteger,.*,java.math.BigDecimal,.*,java.net.InetAddress,.*,java.io.File,.*,java.lang.Object,equals|hashCode" />
                  </inspection_tool>
                  <inspection_tool class="IllegalIdentifier" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="ImplicitArrayToString" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ImplicitNullableNothingType" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="ImplicitSubclassInspection" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="ImplicitThis" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="ImplicitTypeConversion" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="BITS" value="1720" />
                    <option name="FLAG_EXPLICIT_CONVERSION" value="true" />
                    <option name="IGNORE_NODESET_TO_BOOLEAN_VIA_STRING" value="true" />
                  </inspection_tool>
                  <inspection_tool class="IncompatibleMask" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="IncompleteProperty" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="InconsistentResourceBundle" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="IndexOfReplaceableByContains" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="IndexZeroUsage" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="InfiniteLoopStatement" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="InfiniteRecursion" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="InjectedReferences" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="InjectionNotApplicable" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="InspectionDescriptionNotFoundInspection" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="InspectionMappingConsistency" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="InspectionUniqueToolbarId" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="InspectionUsingGrayColors" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="InstantiatingObjectToGetClassObject" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="IntentionDescriptionNotFoundInspection" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="InterfaceMethodClashesWithObject" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="IntroduceWhenSubject" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="InvalidComparatorMethodReference" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="InvalidPropertyKeyForm" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JUnit4AnnotatedMethodInJUnit3TestCase" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JUnit5MalformedParameterized" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JUnit5MalformedRepeated" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JUnit5Platform" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Java8ArraySetAll" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="Java8CollectionRemoveIf" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Java8ListSort" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Java8MapApi" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Java8MapForEach" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Java9CollectionFactory" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Java9ModuleExportsPackageToItself" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Java9RedundantRequiresStatement" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Java9ReflectionClassVisibility" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Java9UndeclaredServiceUsage" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaAbbreviationUsage" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaAccessorMethodCalledAsEmptyParen" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaAccessorMethodOverriddenAsEmptyParen" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaAccessorMethodOverridenAsEmptyParen" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaCollectionsStaticMethod" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaDoc" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="TOP_LEVEL_CLASS_OPTIONS">
                      <value>
                        <option name="ACCESS_JAVADOC_REQUIRED_FOR" value="none" />
                        <option name="REQUIRED_TAGS" value="" />
                      </value>
                    </option>
                    <option name="INNER_CLASS_OPTIONS">
                      <value>
                        <option name="ACCESS_JAVADOC_REQUIRED_FOR" value="none" />
                        <option name="REQUIRED_TAGS" value="" />
                      </value>
                    </option>
                    <option name="METHOD_OPTIONS">
                      <value>
                        <option name="ACCESS_JAVADOC_REQUIRED_FOR" value="none" />
                        <option name="REQUIRED_TAGS" value="@return@param@throws or @exception" />
                      </value>
                    </option>
                    <option name="FIELD_OPTIONS">
                      <value>
                        <option name="ACCESS_JAVADOC_REQUIRED_FOR" value="none" />
                        <option name="REQUIRED_TAGS" value="" />
                      </value>
                    </option>
                    <option name="IGNORE_DEPRECATED" value="false" />
                    <option name="IGNORE_JAVADOC_PERIOD" value="true" />
                    <option name="IGNORE_DUPLICATED_THROWS" value="false" />
                    <option name="IGNORE_POINT_TO_ITSELF" value="false" />
                    <option name="myAdditionalJavadocTags" value="" />
                  </inspection_tool>
                  <inspection_tool class="JavaFxColorRgb" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaFxDefaultTag" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaFxEventHandler" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaFxRedundantPropertyValue" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaFxResourcePropertyValue" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaFxUnresolvedFxIdReference" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaFxUnusedImports" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaLangInvokeHandleSignature" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaModuleNaming" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaMutatorMethodAccessedAsParameterless" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaMutatorMethodOverriddenAsParameterless" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaMutatorMethodOverridenAsParameterless" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaReflectionInvocation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaReflectionMemberAccess" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavaRequiresAutoModule" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavacQuirks" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JavadocReference" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="JoinDeclarationAndAssignment" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="JsonDuplicatePropertyKeys" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="JsonStandardCompliance" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="KDocUnresolvedReference" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="KindProjectorSimplifyTypeProjection" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="KindProjectorUseCorrectLambdaKeyword" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="KotlinDeprecation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="KotlinDoubleNegation" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="KotlinInternalInJava" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="KotlinInvalidBundleOrProperty" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="KotlinMavenPluginPhase" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="KotlinRedundantOverride" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="KotlinTestJUnit" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="KotlinUnusedImport" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="LambdaBodyCanBeCodeBlock" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="LambdaCanBeMethodCall" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="LambdaCanBeReplacedWithAnonymous" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="LambdaParameterTypeCanBeSpecified" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="LanguageFeature" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="LanguageMismatch" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="CHECK_NON_ANNOTATED_REFERENCES" value="true" />
                  </inspection_tool>
                  <inspection_tool class="LastIndexToLast" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="LeakingThis" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="LiftReturnOrAssignment" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="LocalVariableName" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="LongLiteralsEndingWithLowercaseL" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="LoopConditionNotUpdatedInsideLoop" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="ignoreIterators" value="false" />
                  </inspection_tool>
                  <inspection_tool class="LoopStatementsThatDontLoop" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="LoopToCallChain" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="LoopVariableNotUpdated" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="LossyEncoding" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MagicConstant" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MalformedFormatString" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MalformedRegex" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MalformedXPath" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ManualArrayCopy" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ManualArrayToCollectionCopy" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MapFlatten" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MapGetGet" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MapGetOrElseBoolean" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MapKeys" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MapLift" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MapToBooleanContains" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MapValues" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MarkedForRemoval" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="MatchToPartialFunction" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MathRandomCastToInt" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MavenDuplicateDependenciesInspection" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MavenDuplicatePluginInspection" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MavenModelInspection" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="MavenPropertyInParent" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MavenRedundantGroupId" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MayBeConstant" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="MemberVisibilityCanBePrivate" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="MetaAnnotationWithoutRuntimeRetention" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MethodCanBeVariableArityMethod" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="MethodNameSameAsClassName" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MethodNamesDifferOnlyByCase" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MethodOverridesPackageLocalMethod" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MethodOverridesPrivateMethod" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MethodRefCanBeReplacedWithLambda" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="MigrateDiagnosticSuppression" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MismatchedArrayReadWrite" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MismatchedCollectionQueryUpdate" enabled="false" level="WEAK WARNING" enabled_by_default="false">
                    <option name="queryNames">
                      <value />
                    </option>
                    <option name="updateNames">
                      <value />
                    </option>
                    <option name="ignoredClasses">
                      <value />
                    </option>
                  </inspection_tool>
                  <inspection_tool class="MismatchedStringBuilderQueryUpdate" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MissingFinalNewline" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="MissingMnemonic" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MisspelledCompareTo" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MisspelledHashcode" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MisspelledHeader" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="MisspelledToString" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MoveFieldAssignmentToInitializer" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="MoveSuspiciousCallableReferenceIntoParentheses" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="MultiCatchCanBeSplit" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="MultipleArgListsInAnnotation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MultipleRepositoryUrls" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="MutatorLikeMethodIsParameterless" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="NameBooleanParameters" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="NewInstanceOfSingleton" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="NewStringBufferWithCharArgument" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="NoButtonGroup" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="NoExplicitFinalizeCalls" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="NoLabelFor" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="NoReturnTypeForImplicitDef" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="NoScrollPane" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="NonAsciiCharacters" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="NonAtomicOperationOnVolatileField" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="NotImplementedCode" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="NotifyNotInSynchronizedContext" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="NullArgumentToVariableArgMethod" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="NullChecksToSafeCall" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="NullableBooleanElvis" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="NullableProblems" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="REPORT_NULLABLE_METHOD_OVERRIDES_NOTNULL" value="true" />
                    <option name="REPORT_NOT_ANNOTATED_METHOD_OVERRIDES_NOTNULL" value="false" />
                    <option name="REPORT_NOTNULL_PARAMETER_OVERRIDES_NULLABLE" value="true" />
                    <option name="REPORT_NOT_ANNOTATED_PARAMETER_OVERRIDES_NOTNULL" value="true" />
                    <option name="REPORT_NOT_ANNOTATED_GETTER" value="true" />
                    <option name="REPORT_NOT_ANNOTATED_SETTER_PARAMETER" value="true" />
                    <option name="REPORT_ANNOTATION_NOT_PROPAGATED_TO_OVERRIDERS" value="false" />
                    <option name="REPORT_NULLS_PASSED_TO_NON_ANNOTATED_METHOD" value="true" />
                  </inspection_tool>
                  <inspection_tool class="NumberEquality" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="NumericOverflow" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ObjectEquality" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="ObjectEqualsCanBeEquality" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="ObjectEqualsNull" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ObjectLiteralToLambda" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="ObjectPropertyName" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="ObsoleteCollection" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="ignoreRequiredObsoleteCollectionTypes" value="false" />
                  </inspection_tool>
                  <inspection_tool class="ObviousNullCheck" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="OctalLiteral" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="OneButtonGroup" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="OptionEqualsSome" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="OptionalAssignedToNull" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="OptionalIsPresent" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="OptionalUsedAsFieldOrParameterType" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="OverridingDeprecatedMember" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="OverwrittenKey" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="PackageDirectoryMismatch" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="PackageName" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="PackageVisibleInnerClass" enabled="false" level="WARNING" enabled_by_default="false">
                    <scope name="Production" level="WARNING" enabled="false">
                      <option name="ignoreEnums" value="false" />
                      <option name="ignoreInterfaces" value="false" />
                    </scope>
                    <option name="ignoreEnums" value="false" />
                    <option name="ignoreInterfaces" value="false" />
                  </inspection_tool>
                  <inspection_tool class="ParameterCanBeLocal" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ParameterNameDiffersFromOverriddenParameter" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="m_ignoreSingleCharacterNames" value="false" />
                    <option name="m_ignoreOverridesOfLibraryMethods" value="true" />
                  </inspection_tool>
                  <inspection_tool class="ParameterlessMemberOverriddenAsEmptyParen" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ParameterlessMemberOverridenAsEmptyParen" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="PatternNotApplicable" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="PatternOverriddenByNonAnnotatedMethod" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="PatternValidation" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="CHECK_NON_CONSTANT_VALUES" value="true" />
                  </inspection_tool>
                  <inspection_tool class="PlatformExtensionReceiverOfInline" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="PluginXmlValidity" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="PointlessArithmeticExpression" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="m_ignoreExpressionsContainingConstants" value="true" />
                  </inspection_tool>
                  <inspection_tool class="PointlessBitwiseExpression" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="m_ignoreExpressionsContainingConstants" value="true" />
                  </inspection_tool>
                  <inspection_tool class="PointlessBooleanExpression" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="m_ignoreExpressionsContainingConstants" value="true" />
                  </inspection_tool>
                  <inspection_tool class="PointlessNullCheck" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="PostfixTemplateDescriptionNotFound" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="PresentationAnnotation" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="PrimitiveArrayArgumentToVariableArgMethod" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="PrivatePropertyName" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="PropertyName" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="ProtectedInFinal" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="PsiElementConcatenation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="PublicField" enabled="false" level="WARNING" enabled_by_default="false">
                    <scope name="Production" level="WARNING" enabled="false">
                      <option name="ignoreEnums" value="false" />
                      <option name="ignorableAnnotations">
                        <value />
                      </option>
                    </scope>
                    <option name="ignoreEnums" value="false" />
                    <option name="ignorableAnnotations">
                      <value />
                    </option>
                  </inspection_tool>
                  <inspection_tool class="PublicInnerClass" enabled="false" level="WARNING" enabled_by_default="false">
                    <scope name="Production" level="WARNING" enabled="false">
                      <option name="ignoreEnums" value="false" />
                      <option name="ignoreInterfaces" value="false" />
                    </scope>
                    <option name="ignoreEnums" value="false" />
                    <option name="ignoreInterfaces" value="false" />
                  </inspection_tool>
                  <inspection_tool class="QuickFixGetFamilyNameViolation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RangeToIndices" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RawTypeCanBeGeneric" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="RecursiveEqualsCall" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RecursivePropertyAccessor" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantArrayCreation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantBlock" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantCast" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantCollectionConversion" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantCollectionOperation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantComparatorComparing" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantDefaultArgument" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantExplicitClose" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantExplicitType" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantExplicitVariableType" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="RedundantGetter" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantHeadOrLastOption" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantIf" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantImport" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantLambdaArrow" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantLambdaParameterType" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="RedundantModalityModifier" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantNewCaseClass" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantObjectTypeCheck" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantSamConstructor" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantSemicolon" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantSetter" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantStreamOptionalCall" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantStringFormatCall" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantStringOperation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantSuspendModifier" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantThrows" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantThrowsDeclaration" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantTypeArguments" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantTypeConversion" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="CHECK_ANY" value="false" />
                  </inspection_tool>
                  <inspection_tool class="RedundantUnitExpression" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantUnitReturnType" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RedundantVisibilityModifier" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReferenceMustBePrefixed" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReflectionForUnavailableAnnotation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RefusedBequest" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="ignoreEmptySuperMethods" value="false" />
                    <option name="onlyReportWhenAnnotated" value="true" />
                  </inspection_tool>
                  <inspection_tool class="RegExpDuplicateAlternationBranch" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RegExpEmptyAlternationBranch" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RegExpEscapedMetaCharacter" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="RegExpOctalEscape" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="RegExpRedundantEscape" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RegExpRepeatedSpace" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RegExpSingleCharAlternation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RegExpUnexpectedAnchor" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RemoveCurlyBracesFromTemplate" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="RemoveEmptyClassBody" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="RemoveEmptyParenthesesFromLambdaCall" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="RemoveEmptyPrimaryConstructor" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="RemoveEmptySecondaryConstructorBody" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="RemoveExplicitSuperQualifier" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="RemoveExplicitTypeArguments" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="RemoveForLoopIndices" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RemoveRedundantBackticks" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RemoveRedundantCallsOfConversionMethods" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RemoveRedundantReturn" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RemoveRedundantSpreadOperator" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RemoveSetterParameterType" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RemoveSingleExpressionStringTemplate" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="RemoveToStringInStringTemplate" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReplaceAllDot" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReplaceArrayEqualityOpWithArraysEquals" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReplaceArrayOfWithLiteral" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReplaceCallWithBinaryOperator" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReplaceGetOrSet" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="ReplaceInefficientStreamCount" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReplaceNullCheck" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReplacePutWithAssignment" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReplaceRangeToWithUntil" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReplaceSingleLineLet" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReplaceSizeCheckWithIsNotEmpty" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReplaceSizeZeroCheckWithIsEmpty" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReplaceToWithInfixForm" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReplaceToWithUntil" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReplaceWithFlatten" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReplaceWithOperatorAssignment" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="RequiredAttributes" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="myAdditionalRequiredHtmlAttributes" value="" />
                  </inspection_tool>
                  <inspection_tool class="ReturnFromFinallyBlock" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReturnOfDateField" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReturnSeparatedFromComputation" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="ReverseIterator" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReverseMap" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ReverseTakeReverse" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SafeVarargsDetector" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SameElementsToEquals" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SameParameterValue" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SameReturnValue" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SbtReplaceProjectWithProjectIn" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaDefaultFileTemplate" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaDefaultFileTemplateUsage" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaDeprecatedIdentifier" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaDeprecation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaDocInlinedTag" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaDocMissingParameterDescription" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaDocUnbalancedHeader" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaDocUnclosedTagWithoutParser" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaDocUnknownParameter" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaDocUnknownTag" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaFileName" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaMalformedFormatString" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaPackageName" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaRedundantCast" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaRedundantConversion" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaStyle" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaUnnecessaryParentheses" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaUnnecessarySemicolon" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaUnreachableCode" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaUnresolvedPropertyKey" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="ScalaUnusedSymbol" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaUselessExpression" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="ScalaXmlUnmatchedTag" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="ScopeFunctionConversion" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="SecondUnsafeCall" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SelfAssignment" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ShiftOutOfRange" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SillyAssignment" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SimplifiableBooleanExpression" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SimplifiableCallChain" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="SimplifiableConditionalExpression" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SimplifiableFoldOrReduce" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SimplifiableIfStatement" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SimplifiableJUnitAssertion" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SimplifiedTestNGAssertion" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SimplifyAssertNotNull" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="SimplifyBoolean" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SimplifyBooleanMatch" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SimplifyBooleanWithConstants" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="SimplifyCollector" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SimplifyForEach" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="SimplifyNegatedBinaryExpression" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="SimplifyOptionalCallChains" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SimplifyStreamApiCallChains" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SimplifyWhenWithBooleanConstantCondition" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="Since15" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="SingleElementAnnotation" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="SingleImport" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SingleStatementInBlock" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="SingletonConstructor" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="SizeToLength" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SortFilter" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SortModifiers" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="SortedHeadLast" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Specs2Matchers" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SpellCheckingInspection" enabled="false" level="WEAK WARNING" enabled_by_default="false">
                    <option name="processCode" value="true" />
                    <option name="processLiterals" value="true" />
                    <option name="processComments" value="true" />
                  </inspection_tool>
                  <inspection_tool class="StatefulEp" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="StaticInitializerReferencesSubClass" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="StaticPseudoFunctionalStyleMethod" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="StreamToLoop" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="StringBufferReplaceableByString" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="StringBufferReplaceableByStringBuilder" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="StringConcatenationInLoops" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="StringConcatenationInsideStringBufferAppend" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="StringConstructor" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="ignoreSubstringArguments" value="false" />
                  </inspection_tool>
                  <inspection_tool class="StringEquality" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="StringEqualsCharSequence" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="StringTokenizerDelimiter" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SuspiciousArrayMethodCall" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SuspiciousEqualsCombination" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="SuspiciousMethodCalls" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="REPORT_CONVERTIBLE_METHOD_CALLS" value="true" />
                  </inspection_tool>
                  <inspection_tool class="SuspiciousNameCombination" enabled="false" level="WARNING" enabled_by_default="false">
                    <group names="x,width,left,right" />
                    <group names="y,height,top,bottom" />
                    <ignored>
                      <option name="METHOD_MATCHER_CONFIG" value="java.io.PrintStream,println,java.io.PrintWriter,println,java.lang.System,identityHashCode,java.sql.PreparedStatement,set.*,java.sql.ResultSet,update.*,java.sql.SQLOutput,write.*,java.lang.Integer,compare.*,java.lang.Long,compare.*,java.lang.Short,compare,java.lang.Byte,compare,java.lang.Character,compare,java.lang.Boolean,compare,java.lang.Math,.*,java.lang.StrictMath,.*" />
                    </ignored>
                  </inspection_tool>
                  <inspection_tool class="SuspiciousSystemArraycopy" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SuspiciousToArrayCall" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SynchronizationOnGetClass" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SynchronizationOnLocalVariableOrMethodParameter" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="reportLocalVariables" value="true" />
                    <option name="reportMethodParameters" value="true" />
                  </inspection_tool>
                  <inspection_tool class="SynchronizeOnNonFinalField" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="SyntaxError" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="TailRecursion" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="TestFunctionName" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="TestNGDataProvider" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ThrowFromFinallyBlock" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ThrowableInstanceNeverThrown" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ThrowableNotThrown" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ThrowablePrintedToSystemOut" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ThrowableResultOfMethodCallIgnored" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ToArrayCallWithZeroLengthArrayArgument" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ToSetAndBack" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="TooBroadScope" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="TrailingSpacesInProperty" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="TrivialFunctionalExpressionUsage" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="TrivialIf" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="TryFinallyCanBeTryWithResources" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="TryStatementWithMultipleResources" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="TryWithIdenticalCatches" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="TypeAnnotation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="TypeCheckCanBeMatch" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="TypeCustomizer" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="TypeParameterExtendsObject" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="TypeParameterFindViewById" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="TypeParameterHidesVisibleType" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="TypeParameterShadow" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UElementAsPsi" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UNCHECKED_WARNING" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="IGNORE_UNCHECKED_ASSIGNMENT" value="false" />
                    <option name="IGNORE_UNCHECKED_GENERICS_ARRAY_CREATION" value="true" />
                    <option name="IGNORE_UNCHECKED_CALL" value="false" />
                    <option name="IGNORE_UNCHECKED_CAST" value="true" />
                    <option name="IGNORE_UNCHECKED_OVERRIDING" value="false" />
                  </inspection_tool>
                  <inspection_tool class="UNUSED_SYMBOL" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="LOCAL_VARIABLE" value="true" />
                    <option name="FIELD" value="true" />
                    <option name="METHOD" value="false" />
                    <option name="CLASS" value="false" />
                    <option name="PARAMETER" value="true" />
                    <option name="REPORT_PARAMETER_FOR_PUBLIC_METHODS" value="false" />
                  </inspection_tool>
                  <inspection_tool class="UndesirableClassUsage" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnitInMap" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnitMethodIsParameterless" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnknownLanguage" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryAnnotationParentheses" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryBlockStatement" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryBoxing" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryBreak" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryConditionalExpression" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryContinue" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryEmptyArrayUsage" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryEnumModifier" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryFinalOnLocalVariable" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryFinalOnParameter" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="onlyWarnOnAbstractMethods" value="false" />
                  </inspection_tool>
                  <inspection_tool class="UnnecessaryFullyQualifiedName" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="m_ignoreJavadoc" value="true" />
                    <option name="ignoreInModuleStatements" value="true" />
                  </inspection_tool>
                  <inspection_tool class="UnnecessaryInitCause" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryInterfaceModifier" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryLabelOnBreakStatement" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryLabelOnContinueStatement" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryLocalVariable" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="m_ignoreImmediatelyReturnedVariables" value="false" />
                    <option name="m_ignoreAnnotatedVariables" value="false" />
                  </inspection_tool>
                  <inspection_tool class="UnnecessaryModuleDependencyInspection" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryParentheses" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryPartialFunction" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryQualifiedReference" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryReturn" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessarySemicolon" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryTemporaryOnConversionFromString" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryTemporaryOnConversionToString" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryUnboxing" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnnecessaryVariable" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnresolvedPropertyKey" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="UnresolvedReference" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="UnsafeCastFromDynamic" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="UnsafeReturnStatementVisitor" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnsafeVfsRecursion" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnusedAssignment" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="REPORT_PREFIX_EXPRESSIONS" value="false" />
                    <option name="REPORT_POSTFIX_EXPRESSIONS" value="true" />
                    <option name="REPORT_REDUNDANT_INITIALIZER" value="true" />
                  </inspection_tool>
                  <inspection_tool class="UnusedCatchParameter" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="m_ignoreCatchBlocksWithComments" value="false" />
                    <option name="m_ignoreTestCases" value="false" />
                  </inspection_tool>
                  <inspection_tool class="UnusedEquals" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnusedLabel" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnusedLambdaExpressionBody" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnusedMessageFormatParameter" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnusedProperty" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnusedReceiverParameter" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnusedReturnValue" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UnusedSymbol" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="Use of postfix method call" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UseBulkOperation" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UseCompareMethod" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UseDPIAwareBorders" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UseDPIAwareInsets" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UseExpressionBody" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="UseJBColor" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UsePrimitiveTypes" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UsePropertyAccessSyntax" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="UseVirtualFileEquals" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UseWithIndex" enabled="false" level="INFO" enabled_by_default="false" />
                  <inspection_tool class="UselessCallOnCollection" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="UselessCallOnNotNull" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="VarCouldBeVal" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="VarargParameter" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="VariablePatternShadow" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="VariableTypeCanBeExplicit" enabled="false" level="INFORMATION" enabled_by_default="false" />
                  <inspection_tool class="WaitNotInSynchronizedContext" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="WeakerAccess" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="SUGGEST_PACKAGE_LOCAL_FOR_MEMBERS" value="true" />
                    <option name="SUGGEST_PACKAGE_LOCAL_FOR_TOP_CLASSES" value="true" />
                    <option name="SUGGEST_PRIVATE_FOR_INNERS" value="false" />
                  </inspection_tool>
                  <inspection_tool class="WhenWithOnlyElse" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="WhileCanBeForeach" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="WhileLoopSpinsOnField" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="ignoreNonEmtpyLoops" value="true" />
                  </inspection_tool>
                  <inspection_tool class="WrapUnaryOperator" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="WrongPackageStatement" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="WrongPropertyKeyValueDelimiter" enabled="false" level="WEAK WARNING" enabled_by_default="false" />
                  <inspection_tool class="XmlDefaultAttributeValue" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="XmlDuplicatedId" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="XmlHighlighting" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="XmlInvalidId" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="XmlPathReference" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="XmlUnboundNsPrefix" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="XmlUnusedNamespaceDeclaration" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="XmlWrongFileType" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="XmlWrongRootElement" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="XsltDeclarations" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="XsltTemplateInvocation" enabled="false" level="ERROR" enabled_by_default="false" />
                  <inspection_tool class="XsltUnusedDeclaration" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="XsltVariableShadowing" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ZeroIndexToHead" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="ZipWithIndex" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="dependsOnMethodTestNG" enabled="false" level="WARNING" enabled_by_default="false" />
                  <inspection_tool class="groupsTestNG" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="groups">
                      <value>
                        <list size="0" />
                      </value>
                    </option>
                  </inspection_tool>
                  <inspection_tool class="unused" enabled="false" level="WARNING" enabled_by_default="false">
                    <option name="LOCAL_VARIABLE" value="true" />
                    <option name="FIELD" value="true" />
                    <option name="METHOD" value="false" />
                    <option name="CLASS" value="false" />
                    <option name="PARAMETER" value="true" />
                    <option name="REPORT_PARAMETER_FOR_PUBLIC_METHODS" value="false" />
                    <option name="ADD_MAINS_TO_ENTRIES" value="true" />
                    <option name="ADD_APPLET_TO_ENTRIES" value="true" />
                    <option name="ADD_SERVLET_TO_ENTRIES" value="true" />
                    <option name="ADD_NONJAVA_TO_ENTRIES" value="true" />
                  </inspection_tool>
                </profile>
                EOF
                fi
            """.trimIndent()
        }
        ideaInspections {
            id = "RUNNER_246"
            pathToProject = "%MODULE_PATH%/pom.xml"
            jvmArgs = "-Xms2G -Xmx4G -XX:ReservedCodeCacheSize=240m -XX:+UseG1GC"
            targetJdkHome = "%env.JDK_18%"
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
            profilePath = "idea/ignite_inspections_teamcity.xml"
        }
    }

    failureConditions {
        failOnMetricChange {
            id = "BUILD_EXT_5"
            metric = BuildFailureOnMetric.MetricType.INSPECTION_ERROR_COUNT
            threshold = 0
            units = BuildFailureOnMetric.MetricUnit.DEFAULT_UNIT
            comparison = BuildFailureOnMetric.MetricComparison.MORE
            compareTo = value()
            param("anchorBuild", "lastSuccessful")
        }
    }

    requirements {
        equals("teamcity.agent.jvm.os.name", "Linux", "RQ_27")
    }
})
