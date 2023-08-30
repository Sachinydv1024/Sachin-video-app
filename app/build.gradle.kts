plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.videocallapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.videocallapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

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
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth-ktx:22.1.1")
    implementation("com.google.android.gms:play-services-auth:20.6.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("io.agora.rtc:full-sdk:4.0.1")
//    implementation 'com.github.AgoraIO-Community.VideoUIKit-Android:final:v4.0.1'
//    implementation 'com.github.AgoraIO-Community:Android-UIKit:v2.0.0'
//    implementation 'com.github.AgoraIO-Community:VideoUIKit-Android:v4.0.1'
    implementation("commons-codec:commons-codec:1.9")

    //for splash screen animation lottie animation
    implementation("com.airbnb.android:lottie:3.4.0")

    //implementation for dynamic dp
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("io.agora.rtc:chat-sdk:1.1.0")

    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")


    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("de.hdodenhof:circleimageview:3.1.0")

    implementation("com.google.android.exoplayer:exoplayer-core:2.15.0")
    implementation("com.google.android.exoplayer:exoplayer-ui:2.15.0")
    implementation("com.facebook.fresco:fresco:3.0.0")
    implementation("com.squareup.picasso:picasso:2.8")


}