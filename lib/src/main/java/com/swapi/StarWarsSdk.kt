package com.swapi

import android.content.Context
import com.google.gson.GsonBuilder
import com.swapi.network.StarWars
import com.swapi.network.StarWarsRepository
import com.swapi.network.StarWarsRepositoryImpl
import okhttp3.Cache
import okhttp3.OkHttpClient
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

  private const val NETWORK_TIMEOUT_IN_SECONDS = 60L
  private const val NETWORK_CACHE_SIZE = 20 * 1024 * 1024L
  lateinit var repo: StarWarsRepository

  /**
   * Initialize Star wars API with your own [Retrofit] instance
   */
  fun init(retrofit: Retrofit) {
    val api = retrofit.create(StarWars::class.java)
    repo = StarWarsRepositoryImpl(api)
  }

  /**
   * Initialize Star wars API with a dedicated, separate [Retrofit] instance
   */
  fun init(context: Context) {
    val loggingInterceptor = HttpLoggingInterceptor { message -> if (BuildConfig.DEBUG) Timber.d(message) else Timber.i(message) }
    loggingInterceptor.level = (if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.HEADERS else HttpLoggingInterceptor.Level.BASIC)

    val cache = Cache(context.cacheDir, NETWORK_CACHE_SIZE)

    val okHttpClient = OkHttpClient.Builder().apply {
      readTimeout(NETWORK_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
      connectTimeout(NETWORK_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
      addInterceptor(loggingInterceptor)
      retryOnConnectionFailure(true)
      cache(cache)
    }.build()

    val gson = GsonBuilder().setPrettyPrinting().setLenient().create()

    val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build()

    init(retrofit)
  }
}