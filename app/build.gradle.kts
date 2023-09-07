plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.afisdev.registrationform"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.afisdev.registrationform"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_URL", "\"https://api.goapi.id/\"")
            buildConfigField("String", "API_KEY", "\"uXEzN3mTDddFQjhCLAi6rfHV2jkfnS\"")
        }
        getByName("debug") {
            isDebuggable = true
            buildConfigField("String", "API_URL", "\"https://api.goapi.id/\"")
            buildConfigField("String", "API_KEY", "\"uXEzN3mTDddFQjhCLAi6rfHV2jkfnS\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        dataBinding = true
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(project(Modules.common))

    implementation(AndroidLibraries.coreKtx)
    implementation(AndroidLibraries.appCompat)
    implementation(AndroidLibraries.material)
    implementation(AndroidLibraries.constraintLayout)

    implementation(AndroidLibraries.liveData)
    implementation(AndroidLibraries.lifecycleViewModel)

    implementation(AndroidLibraries.navigation)
    implementation(AndroidLibraries.navigationFrag)

    implementation(Libraries.dagger)
    implementation(Libraries.daggerAndroidSupport)
    kapt(Libraries.daggerCompiler)
    kapt(Libraries.daggerAndroidProcessor)

    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitGsonConverter)
    implementation(Libraries.httpLoggingInterceptor)

    implementation(Libraries.timber)

    testImplementation(TestLibraries.junit)

    androidTestImplementation(TestLibraries.junitTestExt)
    androidTestImplementation(TestLibraries.espresso)
}