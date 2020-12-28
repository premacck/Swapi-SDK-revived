package com.swapi.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

internal fun <R, T : Call<R>> T.callApi(init: RetrofitCallBack<R>.() -> Unit) {
  val callback = RetrofitCallBack<R>()
  callback.init()
  enqueue(callback)
}

class RetrofitCallBack<T> : Callback<T> {

  private var responseListener: ((response: Response<T>) -> Unit)? = null
  private var failureListener: ((t: Throwable) -> Unit)? = { Timber.e(it) }

  override fun onResponse(call: Call<T>, response: Response<T>) {
    responseListener?.invoke(response)
  }

  override fun onFailure(call: Call<T>, t: Throwable) {
    failureListener?.invoke(t)
  }

  fun response(init: (response: Response<T>) -> Unit) {
    responseListener = init
  }

  fun failure(init: ((t: Throwable) -> Unit)) {
    failureListener = init
  }
}