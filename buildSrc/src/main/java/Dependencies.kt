object Modules {
    const val app = ":app"
    const val common = ":common"
}

object Versions {
    const val kotlin = "1.8.10"
    const val coroutines = "1.6.0"
    const val appCompat = "1.6.1"
    const val material = "1.9.0"

    const val lifecycle = "2.6.1"
    const val hilt = "2.44"
    const val gson = "2.10.1"
    const val okHttp = "5.0.0-alpha.11"
    const val retrofit = "2.9.0"

    const val nav = "2.5.3"
    const val core = "1.10.1"
    const val constraintLayout = "2.1.4"

    const val timber = "5.0.1"

    const val multiDex = "2.0.1"

    const val androidTestRunner = "1.4.0"
    const val espressoCore = "3.4.0"
    const val androidJunit = "1.1.5"
    const val junit = "4.13.2"
    const val mockk = "1.12.1"
    const val archCoreTest = "2.1.0"
}

object Libraries {
    // Hilt
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    // RETROFIT
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    // LOGGER
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object AndroidLibraries {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.core}"
    const val core = "androidx.core:core:${Versions.core}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // KOTLIN
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // Support library depends on this lightweight import
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val lifecycleCommon = "androidx.lifecycle:lifecycle-common:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

    // NAVIGATION
    const val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"
    const val navigationFrag = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
    const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.nav}"

    // MULTIDEX
    const val multiDex = "androidx.multidex:multidex:${Versions.multiDex}"
}

object TestLibraries {
    // ANDROID TEST
    const val androidTestRunner = "androidx.test:runner:${Versions.androidTestRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espressoCore}"
    const val junitTestExt= "androidx.test.ext:junit:${Versions.androidJunit}"
    const val junit = "junit:junit:${Versions.junit}"

    // TEST
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val archCoreTest = "androidx.arch.core:core-testing:${Versions.archCoreTest}"
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"


    // COROUTINE
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}
