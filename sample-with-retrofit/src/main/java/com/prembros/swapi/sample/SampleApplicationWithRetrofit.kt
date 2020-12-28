package com.prembros.swapi.sample

import android.app.Application
import com.google.gson.GsonBuilder
import com.swapi.BuildConfig
import com.swapi.StarWarsSdk
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Prem's creation, on 28/12/20
 */
class SampleApplicationWithRetrofit : Application() {

  private lateinit var retrofit: Retrofit

  override fun onCreate() {
    super.onCreate()
    retrofit = getRetrofit()

    // If you don't have your custom Retrofit instance, you can initialize fresh
    // StarWarsSdk.init(applicationContext)

    // If you have your own Retrofit instance, you can initialize it with that
    StarWarsSdk.init(retrofit)
  }

  private fun getRetrofit(): Retrofit {
    val loggingInterceptor = HttpLoggingInterceptor { message -> if (BuildConfig.DEBUG) Timber.d(message) else Timber.i(message) }
    loggingInterceptor.level = (if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.HEADERS else HttpLoggingInterceptor.Level.BASIC)

    val cache = Cache(applicationContext.cacheDir, StarWarsSdk.STAR_WARS_NETWORK_CACHE_SIZE)

    val okHttpClient = OkHttpClient.Builder()
        .readTimeout(StarWarsSdk.STAR_WARS_NETWORK_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .connectTimeout(StarWarsSdk.STAR_WARS_NETWORK_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .retryOnConnectionFailure(true)
        .cache(cache)
        .build()

    val gson = GsonBuilder().setPrettyPrinting().setLenient().create()

    return Retrofit.Builder()
        .baseUrl("https://example.com/")   // Custom URL (any URL other than Swapi URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
  }
}