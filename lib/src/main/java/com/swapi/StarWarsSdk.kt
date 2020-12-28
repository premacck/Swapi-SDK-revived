package com.swapi

import android.content.Context
import com.google.gson.GsonBuilder
import com.swapi.network.*
import okhttp3.Cache
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Prem's creation, on 28/12/20
 *
 * The Star Wars SDK base class. Contains network components required to make API calls
 */
object StarWarsSdk {

  const val STAR_WARS_NETWORK_TIMEOUT_IN_SECONDS = 60L
  const val STAR_WARS_NETWORK_CACHE_SIZE = 20 * 1024 * 1024L
  lateinit var repo: StarWarsRepository

  /**
   * Initialize Star wars API with your own [Retrofit] instance
   */
  fun init(retrofit: Retrofit) {
    val api = retrofit.create(StarWarsApiOverrideService::class.java)
    init(StarWarsRepositoryOverrideImpl(api))
  }

  private fun init(repo: StarWarsRepository) {
    this.repo = repo
  }

  /**
   * Initialize Star wars API with a dedicated, separate [Retrofit] instance
   */
  fun init(context: Context) {
    val loggingInterceptor = HttpLoggingInterceptor { message -> if (BuildConfig.DEBUG) Timber.d(message) else Timber.i(message) }
    loggingInterceptor.level = (if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.HEADERS else HttpLoggingInterceptor.Level.BASIC)

    val cache = Cache(context.cacheDir, STAR_WARS_NETWORK_CACHE_SIZE)

    val okHttpClient = UnsafeOkHttpClient.getBuilder()
        .readTimeout(STAR_WARS_NETWORK_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .connectTimeout(STAR_WARS_NETWORK_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .retryOnConnectionFailure(true)
        .cache(cache)
        .build()

    val gson = GsonBuilder().setPrettyPrinting().setLenient().create()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://swapi.dev/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    val api = retrofit.create(StarWarsApiService::class.java)
    init(StarWarsRepositoryImpl(api))
  }
}