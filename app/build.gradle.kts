plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.dokka")
}

android {
    namespace = "com.example.hotel"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.hotel"
        minSdk = 24
        targetSdk = 34
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")

    val okhttp3_version = "4.9.1"
    implementation("com.squareup.okhttp3:okhttp:$okhttp3_version")

    val coroutines_version = "1.7.1"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    val koin_version = "3.5.0"
    implementation("io.insert-koin:koin-core:$koin_version")
    implementation("io.insert-koin:koin-android:$koin_version")
    testImplementation("io.insert-koin:koin-test:$koin_version")

    val lifecycle_version = "2.6.2"
    implementation("androidx.lifecycle:lifecycle-common:$lifecycle_version")

    implementation(project(path = ":domain"))
    implementation(project(path = ":data"))

    implementation("androidx.viewpager2:viewpager2:1.0.0")

    implementation("com.squareup.picasso:picasso:2.8")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    implementation("com.xw.repo:bubbleseekbar:3.18")

    implementation("com.github.google:flexbox-layout:3.0.0")

    implementation("com.github.bumptech.glide:glide:4.12.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}