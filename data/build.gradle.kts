plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.kyj.data"
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":domain"))

    implementation(Ktx.CORE)
    implementation(AndroidX.APP_COMPAT)
    implementation(Google.MATERIAL)
    testImplementation(Test.JUNIT)
    androidTestImplementation(AndroidTest.EXT_JUNIT)
    androidTestImplementation(AndroidTest.ESPRESSO_CORE)

    //Retrofit
    implementation(Retrofit.RETROFIT)
    implementation(Retrofit.CONVERTER_SERIALIZATION)

    //OkHttp
    implementation(OkHttp.OKHTTP)
    implementation(OkHttp.LOGGING_INTERCEPTOR)

    //timber
    implementation(Timber.TIMBER)

    //hilt
    implementation(Hilt.HILT_ANDROID)
    kapt(Hilt.HILT_ANDROID_COMPILER)

    //Serialization
    implementation(Serialization.SERIALIZATION)

    //Room
    implementation(Room.ROOM_KTX)
    implementation(Room.ROOM_RUNTIME)
    kapt(Room.ROOM_COMPILER)
}