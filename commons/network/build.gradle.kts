dependencies {

    api(project(projects.library.Commons.Modules.extensions))

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

    // OkHttp3
    implementation(libs.okhttp3.logging.interceptor)

    // Retrofit
    implementation(libs.retrofit2.retrofit)
    implementation(libs.moshi.kotlin)
    implementation(libs.retrofit2.converter.moshi)
    implementation(libs.rxjava2.rxjava.adapter)
    implementation(libs.retrofit2.converter.gson)

    // Moshi
    implementation(libs.moshi.kotlin)

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


}