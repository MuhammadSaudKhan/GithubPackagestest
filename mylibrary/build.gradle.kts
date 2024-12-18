import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    `maven-publish`
}

android {
    namespace = "com.saud.mylibrary"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    val githubProperties = Properties().apply {
        load(rootProject.file("github.properties").inputStream())
    }
    val getVersionName: () -> String = { "1.0.2" } // Replace with the desired version name
    val getArtifactId: () -> String = { "sampleAndroidLib" } // Replace with the library name ID
    publishing {
//        publications {
//            maven("bar", MavenPublication::class) {
//                groupId = "com.saudkhan.libraries" // Replace with your group ID
//                artifactId = "sampleAndroidLib" // Replace with your artifact ID
//                version = "1.0.2" // Replace with your version
//
//                // Specify the artifact directly
//                artifact("$buildDir/outputs/aar/sampleAndroidLib-release.aar")
//            }
//        }
//        publications {
//            maven {
//                groupId = "com.saudkhan.libraries"
//                artifactId = "artifactId"
//                version = "versionName"
//
//                // Specify the AAR artifact
//                artifact("$buildDir/outputs/aar/release.aar")
//            }
//        }
        repositories {
            maven {
                name = "GitHubPackages"
                // Replace with your GitHub user ID and repository name
                url = uri("https://maven.pkg.github.com/MuhammadSaudKhan/REPOSITORY")
                credentials {
                    // Use properties file or environment variables for authentication
                    username = githubProperties["gpr.usr"]?.toString() ?: System.getenv("GPR_USER")
                    password = githubProperties["gpr.key"]?.toString() ?: System.getenv("GPR_API_KEY")
                }
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}