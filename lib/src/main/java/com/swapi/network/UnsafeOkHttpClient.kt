package com.swapi.network

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException

/**
 * Prem's creation, on 29/12/20
 */
@SuppressLint("TrustAllX509TrustManager") object UnsafeOkHttpClient {
  fun getBuilder(): OkHttpClient.Builder {
    return try {
      // Create a trust manager that does not validate certificate chains
      val trustAllCerts: Array<TrustManager> = arrayOf(
        object : X509TrustManager {
          @Throws(CertificateException::class) override fun checkClientTrusted(chain: Array<X509Certificate?>?, authType: String?) {}
          @Throws(CertificateException::class) override fun checkServerTrusted(chain: Array<X509Certificate?>?, authType: String?) {}
          override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        }
      )

      // Install the all-trusting trust manager
      val sslContext: SSLContext = SSLContext.getInstance("SSL")
      sslContext.init(null, trustAllCerts, SecureRandom())

      // Create an ssl socket factory with our all-trusting manager
      val sslSocketFactory = sslContext.socketFactory
      OkHttpClient.Builder()
          .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
          .hostnameVerifier { _, _ -> true }
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }
}