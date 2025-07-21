plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

val mapsApiKey: String = project.findProperty("MAPS_API_KEY") as? String ?: "DUMMY_KEY"

android {
    namespace = "com.example.s13_briceno_cuchareableappgooglemaps"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.s13_briceno_cuchareableappgooglemaps"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        resValue("string", "google_maps_key", mapsApiKey)
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Google maps
    implementation ("com.google.android.gms:play-services-maps:18.2.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.1")
    //Material Design
    implementation("com.google.android.material:material:1.12.0")

}