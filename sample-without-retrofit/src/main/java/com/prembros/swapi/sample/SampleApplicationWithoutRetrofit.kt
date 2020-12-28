package com.prembros.swapi.sample

import android.app.Application
import com.swapi.StarWarsSdk

/**
 * Prem's creation, on 28/12/20
 */
class SampleApplicationWithoutRetrofit : Application() {

  override fun onCreate() {
    super.onCreate()
    // If you don't have your custom Retrofit instance, you can initialize fresh
    StarWarsSdk.init(applicationContext)

    // If you have your own Retrofit instance, you can initialize it with that
    //StarWarsSdk.init(retrofit)
  }
}