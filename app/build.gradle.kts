plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("androidx.navigation.safeargs")
    id("com.google.dagger.hilt.android")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        dataBinding = true
    }
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
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

    implementation(Libraries.hiltAndroid)
    kapt(Libraries.hiltAndroidCompiler)

    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitGsonConverter)
    implementation(Libraries.httpLoggingInterceptor)

    implementation(Libraries.timber)

    implementation(AndroidLibraries.multiDex)

    testImplementation(TestLibraries.junit)

    androidTestImplementation(TestLibraries.junitTestExt)
    androidTestImplementation(TestLibraries.espresso)
}