import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)

    // Kotlin Serialization
    alias(libs.plugins.kotlinSerialization)

    // Google Services for Firebase
    alias(libs.plugins.google.services)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm()

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            // ktor for networking - OkHttp client
            implementation(libs.ktor.client.okhttp)

            // Koin - Dependency Injection
            implementation(libs.koin.android)

            // Firebase BOM
            implementation(project.dependencies.platform(libs.firebase.bom))

            // Firebase Auth
            implementation(libs.firebase.auth)
        }
        commonMain.dependencies {

            // Voyager Navigation
            implementation(libs.voyager.navigator)

            // Voyager transitions effect
            implementation(libs.voyager.transitions)

            // Voyager Navigation Bar library
            implementation(libs.voyager.tabNavigator)

            // Material Icons Extended
            implementation(libs.material.icons.extended)

            // Koin - Dependency Injection
            implementation(libs.koin.compose)
            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)

            // Multiplatform Preferences
            implementation(libs.multiplatform.settings)

            // Ktor for networking - Common
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.negotiation)

            // Ktor serialization - JSON
            implementation(libs.ktor.serialization.kotlinx.json)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
        }
        iosMain.dependencies {
            // ktor for networking - Darwin client
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.quickstore"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.quickstore"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.quickstore.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.quickstore"
            packageVersion = "1.0.0"
        }
    }
}
