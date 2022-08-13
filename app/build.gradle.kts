plugins {
    id(Plugins.androidApp)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.hilt)
}

android {

    compileSdk = ConfigData.compileSdkVersion
    defaultConfig {
        applicationId = ConfigData.applicationId
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Versions.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_version
    }
}

dependencies {

    implementation(project(":core"))

    // KTX:
    implementation(Deps.ktxActivity)
    implementation(Deps.ktxCollection)
    implementation(Deps.ktxCore)
    implementation(Deps.ktxFrag)

    // Hilt:
    implementation(Deps.hilt)
    kapt(Deps.hiltCompiler)
    implementation(Deps.hiltNavFrag)

    // Compose:
    implementation(Deps.composeUi)
    implementation(Deps.composeMaterial)
    implementation(Deps.composeTooling)
    implementation(Deps.composeActivity)
    androidTestImplementation(Deps.composeJunitTest)
    debugImplementation(Deps.debugComposeUiTooling)
    debugImplementation(Deps.debugComposeUiTestManifest)

    // ViewModels:
    implementation(Deps.lifecycleViewModel)
    implementation(Deps.lifecycleLiveData)

    implementation(Deps.retrofit)
    implementation(Deps.retrofitInterceptor)
    implementation(Deps.retrofitConverter)
    implementation(Deps.moshiKotlin)

    // Room:
    implementation(Deps.roomRuntime)
    implementation(Deps.roomKtx)
    kapt(Deps.roomCompiler)

    implementation(Deps.materialDesign)

    // UI:
    implementation(Deps.appcompat)

    // Coroutines:
    implementation(Deps.coroutines)

    // Debug:
    implementation(Deps.timber)

    // Test:
    testImplementation(Deps.junit)
    androidTestImplementation(Deps.testRunner)
    testImplementation(Deps.testKotlinCoroutines)
    androidTestImplementation(Deps.testEspressoCore)
    androidTestImplementation(Deps.testEspressoContrib)
    androidTestImplementation(Deps.testExtJunit)
}