plugins {
    id("com.android.application")
//    id("dagger.hilt.android.plugin:2.35")
    kotlin("android")
    `maven-publish`
    id("de.menkalian.vela.versioning")
    id("kotlin-android")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    signingConfigs {
        create("release") {
            storeFile = file(System.getenv("SIGNING_KEYSTORE_LOCATION") ?: "keystore.jks")
            storePassword = System.getenv("SIGNING_KEYSTORE_PASS")
            keyAlias = System.getenv("SIGNING_KEY_ALIAS")
            keyPassword = System.getenv("SIGNING_KEY_PASS")

            this.isV1SigningEnabled = true
            this.isV2SigningEnabled = true
        }
    }

    defaultConfig {
        applicationId("de.menkalian.apus")
        minSdkVersion(26)
        targetSdkVersion(30)
        versionCode(versioning.buildNo)
        versionName("1.0.0_${versioning.buildNo}")

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix(".debug")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.findByName("release")!!
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    // Kotlin
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2")

    // Android Libs
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.activity:activity-ktx:1.2.2")
    implementation("androidx.fragment:fragment:1.3.3")
    implementation("androidx.fragment:fragment-ktx:1.3.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")

    implementation("com.google.android.material:material:1.4.0-alpha02")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

//    implementation("com.google.dagger:hilt-android:2.35")
//    kapt("com.google.dagger:hilt-compiler:2.35")

    add("testImplementation", "org.junit.jupiter:junit-jupiter-api:5.7.0")
    add("testImplementation", "org.junit.jupiter:junit-jupiter-params:5.7.0")
    add("testRuntimeOnly", "org.junit.jupiter:junit-jupiter-engine:5.7.0")

    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
}

tasks.withType(Test::class) {
    if (this.name.equals("test"))
        useJUnitPlatform()
}
