plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.apollographql.apollo3").version("3.7.3")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id ("com.google.devtools.ksp")
}

apollo {
    service("service") {
        generateKotlinModels.set(true)
        packageName.set("com.plcoding")
    }
}

android {
    namespace = "com.plcoding.graphqlcountriesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.plcoding.graphqlcountriesapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.compose.ui:ui:1.5.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.2")
    implementation("androidx.compose.material:material:1.5.2")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.2")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.2")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.2")

    //GraphQl
    implementation("com.apollographql.apollo3:apollo-runtime:3.7.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")
    kapt("androidx.hilt:hilt-compiler:1.1.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.3.0")

    //Glide
    implementation ("com.github.bumptech.glide:compose:1.0.0-beta01")

    //Splash
    implementation ("androidx.core:core-splashscreen:1.0.0-alpha02")

    //Fonts
    implementation("androidx.compose.ui:ui-text-google-fonts:1.5.4")
}