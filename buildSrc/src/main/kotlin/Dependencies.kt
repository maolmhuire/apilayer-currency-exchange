object Plugins {
    const val androidApp = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val kotlinKapt = "kotlin-kapt"
    const val hilt = "dagger.hilt.android.plugin"
}

object Deps {
    const val ktxActivity = "androidx.activity:activity-ktx:1.5.1"
    const val ktxCollection = "androidx.collection:collection-ktx:1.2.0"
    const val ktxCore = "androidx.core:core-ktx:1.8.0"
    const val ktxFrag = "androidx.fragment:fragment-ktx:1.5.1"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val materialDesign = "com.google.android.material:material:${Versions.material}"

    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
    const val navUi = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.3"
    const val retrofitConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.13.0"

    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hiltNavFrag = "androidx.hilt:hilt-navigation-fragment:1.0.0"
    const val hiltClasspath = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
    const val appcompat = "androidx.appcompat:appcompat:1.4.2"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler =  "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    // Compose:
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose_version}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose_version}"
    const val composeTooling = "androidx.compose.ui:ui-tooling-preview:${Versions.compose_version}"
    const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.compose_version}"
    const val composeActivity = "androidx.activity:activity-compose:1.5.1"
    const val composeJunitTest = "androidx.compose.ui:ui-test-junit4:${Versions.compose_version}"
    const val debugComposeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose_version}"
    const val debugComposeUiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose_version}"
    const val composeLifecycle = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
    const val composeLiveData = "androidx.compose.runtime:runtime-livedata:1.2.1"

    // Test:
    const val junit = "junit:junit:${Versions.jUnit}"
    const val testEspressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val testExtJunit = "androidx.test.ext:junit:1.1.3"
    const val testEspressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val testRunner = "com.android.support.test:runner:1.0.2"
    const val testKotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1"
}