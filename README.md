SWAPI-Android-SDK (revived)
=================

The SWAPI (Star Wars API) SDK for Android, converted to kotlin, revived from the good old SDK at https://github.com/Oleur/SWAPI-Android-SDK

SWAPI SDk requires at minimum Java 8 or Android API 21.

## How to use it

Create a global instance in your application class

- If you have your own `Retrofit` instance in your app, you can just use that one
```kotlin
class SampleApplicationWithRetrofit : Application() {

  private lateinit var retrofit: Retrofit

  override fun onCreate() {
    super.onCreate()
    retrofit = getRetrofit()
    StarWarsSdk.init(retrofit)
  }

  private fun getRetrofit(): Retrofit {
    // Retrofit initialization, or inject from DI
  }
}
```

- If you don't have your own `Retrofit` instance in your app, or don't want to use your implementation of it with Star Wars SDK, you can use the alternative initialization approach:
```kotlin
class SampleApplicationWithoutRetrofit : Application() {

  override fun onCreate() {
    super.onCreate()
    StarWarsSdk.init(applicationContext)
  }
}
````

In your activities or fragment you may fire every API call you want.
```kotlin
StarWarsSdk.repo.getAllPlanets(2) {
  onResponse { response ->
    // Handle response here
  }
  onFailure { throwable ->
    // Handle network failures here
  }
  onError { throwable ->
    // Handle unexpected/local errors here
  }
}
```

Or you can Create a common `RetrofitCallBack`, as implemented in [ListActivity](https://github.com/premacck/Swapi-SDK-revived/blob/main/sample-components/src/main/java/com/prembros/swapi/sample_components/ListActivity.kt)

