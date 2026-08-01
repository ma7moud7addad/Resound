plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.typezero.resound"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.typezero.resound"
        minSdk = 26
        targetSdk = 35
        versionCode = 13
        versionName = "0.7.0"

        buildConfigField(
            "String",
            "UPDATE_MANIFEST_URL",
            "\"https://raw.githubusercontent.com/MikereDD/It-Works-On-My-Machine/main/Android/Resound/releases/update.json\"",
        )

        // FFmpeg ships large native libs — restrict ABIs to keep the APK sane.
        ndk {
            abiFilters += listOf("arm64-v8a")
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    // Kotlin sources live under src/main/kotlin per repo convention.
    sourceSets["main"].kotlin.srcDir("src/main/kotlin")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    debugImplementation(libs.androidx.ui.tooling)

    implementation(libs.kotlinx.coroutines.android)

    // ── FFmpeg engine ───────────────────────────────────────────────────────
    // ffmpeg-kit was retired by arthenica and pulled from Maven Central. This is
    // a community republish rebuilt for the 16KB page size that Android 15 / API
    // 35 requires (the old 4KB-aligned 6.0 binaries SIGBUS on modern devices).
    // It keeps the original com.arthenica.ffmpegkit wrapper API and pulls
    // smart-exception transitively. Swap the coordinate if you prefer another
    // republish (e.g. io.github.maitrungduc1410:ffmpeg-kit-audio:6.0.1).
    implementation("com.moizhassan.ffmpeg:ffmpeg-kit-16kb:6.1.1")
}
