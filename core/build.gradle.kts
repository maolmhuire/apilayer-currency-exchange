plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.hilt)
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(Deps.ktxCore)

    // Hilt:
    implementation(Deps.hilt)
    kapt(Deps.hiltCompiler)
    implementation(Deps.hiltNavFrag)

    // Retrofit:
    implementation(Deps.retrofit)
    implementation(Deps.retrofitInterceptor)
    implementation(Deps.retrofitConverter)
    implementation(Deps.moshiKotlin)

    // Room:
    implementation(Deps.roomRuntime)
    implementation(Deps.roomKtx)
    kapt(Deps.roomCompiler)

    testImplementation(Deps.junit)
    androidTestImplementation(Deps.testExtJunit)
    testImplementation(Deps.testKotlinCoroutines)
}