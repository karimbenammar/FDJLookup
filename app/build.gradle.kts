plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "fr.fdj.lookup"
    compileSdk = 33

    defaultConfig {
        applicationId = "fr.fdj.lookup"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "BASE_URL", "\"https://www.thesportsdb.com/api/v1/json/50130162/\"")
    }

    buildTypes {
        getByName("release") {
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    // Compose
    implementation(platform(Libs.composeBOM))
    implementation(Libs.composeUI)
    implementation(Libs.composeActivities)
    implementation(Libs.composeGraphics)
    implementation(Libs.composeToolingPreview)
    implementation(Libs.composeMaterial3)
    implementation(Libs.composeNavigation)

    // Hilt
    implementation(Libs.hilt)
    implementation(Libs.hilt_compose)
    kapt(Libs.hilt_compiler)
    kapt(Libs.hilt_kapt)

    // Retrofit
    implementation(Libs.retrofit2)
    implementation(Libs.retrofit2_gson_converter)

    // Landscapist
    implementation(Libs.landscapist_glide)
    implementation(Libs.landscapist_animation)
    implementation(Libs.landscapist_placeholder)

    testImplementation(Libs.junit)
    testImplementation(Libs.kotlin_coroutines_test)
    testImplementation(Libs.mockito)
}