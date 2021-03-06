# Base Repo for easy and fast integrtation.
- version 1.0.1
- Following Steps for integration

<code>
plugins {
    id 'kotlin-kapt'
}

dataBinding {
   enabled = true
}

viewBinding {
  enabled = true
}

dependencies {

    implementation 'androidx.core:core-ktx:1.8.0'
    implementation 'androidx.appcompat:appcompat:1.4.2'
    implementation 'com.google.android.material:material:1.6.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.2.0'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0'
    implementation 'androidx.swiperefreshlayout:swiperefreshlayout:1.1.0'
    implementation 'androidx.databinding:databinding-runtime:4.2.2'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    // Glide
    def glide_version = "4.11.0"
    api "com.github.bumptech.glide:glide:$glide_version"
    api "com.github.bumptech.glide:okhttp3-integration:$glide_version"
    annotationProcessor "com.github.bumptech.glide:compiler:$glide_version"
    kapt "com.github.bumptech.glide:compiler:$glide_version"

    // Retrofit Okhttp
    def retrofit_version = '2.6.2'
    def okhttp_interceptor_version = '4.3.1'
    api "com.squareup.retrofit2:retrofit:$retrofit_version"
    api "com.squareup.retrofit2:converter-moshi:$retrofit_version"
    api "com.squareup.okhttp3:logging-interceptor:$okhttp_interceptor_version"
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'


    // moshi kotlin factory
    def moshi_kotlin_version = '1.9.2'
    api "com.squareup.moshi:moshi-kotlin:$moshi_kotlin_version"
    api "com.squareup.moshi:moshi-adapters:$moshi_kotlin_version"

    // Navigation
    def nav_version = "2.5.0"
    api "androidx.navigation:navigation-fragment-ktx:$nav_version"
    api "androidx.navigation:navigation-ui-ktx:$nav_version"

    def paging = "3.1.1"
    api "androidx.paging:paging-runtime:$paging"

    def koin_version = "3.2.0"
    implementation "io.insert-koin:koin-android:$koin_version"

    // Koin Test
    testImplementation "io.insert-koin:koin-test:$koin_version"
    testImplementation "io.insert-koin:koin-test-junit4:$koin_version"

    // Navigation
    api "androidx.navigation:navigation-fragment-ktx:$nav_version"
    api "androidx.navigation:navigation-ui-ktx:$nav_version"
    api "androidx.fragment:fragment-ktx:1.3.6"
}
</code>

