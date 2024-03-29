object Modules {
    const val app = ":app"
    const val common = ":common"
    const val featureAuth = ":feature-auth"
    const val featureConductor = ":feature-conductor"
    const val featurePassenger = ":feature-passenger"
    const val local = ":local"
    const val model = ":model"
    const val navigation = ":navigation"
    const val remote = ":remote"
    const val repository = ":repository"
}

object Config {
    const val kotlinVersion = "1.3.50"
    const val compileSdkVersion = 29
    const val minSdkVersion = 21
    const val targetSdkVersion = 29
    const val versionCode = 1
    const val versionName = "1.0"
    const val applicationId = "com.minosai.offlineticket"
}

object Versions {

    //Core
    const val androidx = "1.1.0-rc01"

    //Kotlin
    const val ktx = "1.1.0"
    const val coroutines = "1.3.0-RC"
    const val koin = "1.0.2"

    // AAC
    const val lifecycle = "2.2.0-alpha03"
    const val room = "2.2.0-beta01"
    const val navigation = "2.1.0"
    const val paging = "2.1.0"

    //Networking
    const val gson = "2.8.5"
    const val retrofit = "2.6.0"
    const val okHttp = "3.12.1"

    //UI
    const val constraintLayout = "2.0.0-beta2"
    const val fragment = "1.2.0-alpha03"
    const val material = "1.2.0-alpha01"
    const val recyclerView = "1.1.0-beta04"
    const val rippleAnim = "1.0.1"

    const val chirp = "3.10.+"

    //Test
    const val junit = "4.12"
    const val androidxTest = "1.2.0"
    const val espresso = "3.2.0"
}

object Libs {

    //Core
    const val androidxAppCompat = "androidx.appcompat:appcompat:" + Versions.androidx
    const val androidxCore = "androidx.core:core-ktx:" + Versions.ktx

    //Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:" + Config.kotlinVersion
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:" + Versions.coroutines
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:" + Versions.coroutines
    const val koinAndroid ="org.koin:koin-android:" + Versions.koin
    const val koinViewModel = "org.koin:koin-android-viewmodel:" + Versions.koin

    //AAC
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:" + Versions.lifecycle
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:" + Versions.lifecycle
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:" + Versions.lifecycle
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:" + Versions.lifecycle
    const val roomCompiler = "androidx.room:room-compiler:" + Versions.room
    const val roomRunTime = "androidx.room:room-runtime:" + Versions.room
    const val roomKtx = "androidx.room:room-ktx:" + Versions.room
    const val paging = "androidx.paging:paging-runtime-ktx:" + Versions.paging
    const val navigation = "androidx.navigation:navigation-ui-ktx:" + Versions.navigation
    const val navigationFrag = "androidx.navigation:navigation-fragment-ktx:" + Versions.navigation

    //Networking
    const val gson = "com.google.code.gson:gson:" + Versions.gson
    const val retrofit = "com.squareup.retrofit2:retrofit:" + Versions.retrofit
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:" + Versions.retrofit
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:" + Versions.okHttp

    //UI
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:" + Versions.constraintLayout
    const val fragment = "androidx.fragment:fragment-ktx:" + Versions.fragment
    const val recyclerView = "androidx.recyclerview:recyclerview:" + Versions.recyclerView
    const val materialComponents = "com.google.android.material:material:" + Versions.material
    const val rippleAnim = "com.skyfishjy.ripplebackground:library:" + Versions.rippleAnim

    const val chirpSdk = "io.chirp:chirpsdk:" + Versions.chirp

    //Test
    const val junit = "junit:junit:" + Versions.junit
    const val androidxTestRunner = "androidx.test:runner:" + Versions.androidxTest
    const val espresso = "androidx.test.espresso:espresso-core:" + Versions.espresso
}