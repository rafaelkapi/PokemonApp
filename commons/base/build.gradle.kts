dependencies {
    // Kotlin
    implementation(libs.kotlin.stdlib.jdk7)
    implementation(libs.kotlin.reflect)

    // AndroidUi
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.google.android.material)
    implementation(libs.androidx.dynamicanimation)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment)

    implementation(libs.androidx.lifecycle.common)

    // ViewModel
//    implementation(libs.androidx.lifecycle.extensions)
//    implementation(libs.androidx.lifecycle.reactivestreams)

    // Dagger
    implementation(libs.google.dagger.core)
//    implementation(libs.google.dagger.support)
    implementation(libs.google.dagger.android)
    kapt(libs.google.dagger.compiler)
    kapt(libs.google.dagger.processor)

    // Reactivex
    implementation(libs.rxjava2.rxandroid)
    implementation(libs.rxjava2.rxkotlin)
    implementation(libs.rxjava2.rxjava)

    // Databinding
    implementation(libs.androidx.viewbinding)
}