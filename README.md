SWAPI-Android-SDK (revived)
=================

[![](https://jitpack.io/v/premacck/Swapi-SDK-revived.svg)](https://jitpack.io/#premacck/Swapi-SDK-revived)

The SWAPI (Star Wars API) SDK for Android, converted to kotlin, revived from the good old SDK at https://github.com/Oleur/SWAPI-Android-SDK

SWAPI SDk requires at minimum Java 8 or Android API 21.

## Integration

### Gradle

- Add the following in your root build.gradle at the end of repositories:
```gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

- For the SDK Library:
```gradle
implementation 'com.github.premacck.Swapi-SDK-revived:Swapi-Revived:release-1.1.2'
```

- For the UI components (without the Application class):
(You can use this to quickly implement the star wars sample in your project, which could be a library sample)
```gradle
implementation 'com.github.premacck.Swapi-SDK-revived:swapi-sample-components:release-1.1.2'
```

### Maven

- Add the JitPack repository to your build file:
```XML
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

- Add the dependency (for SDK)
```XML
<dependency>
  <groupId>com.github.premacck.Swapi-SDK-revived</groupId>
  <artifactId>Swapi-Revived</artifactId>
  <version>release-1.1.1</version>
</dependency>
```

- Add the dependency (for UI components)
```XML
<dependency>
  <groupId>com.github.premacck.Swapi-SDK-revived</groupId>
  <artifactId>swapi-sample-components</artifactId>
  <version>release-1.1.1</version>
</dependency>
```

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

Or you can Create a common `retrofitCallBack()`, as implemented in [SwapiListActivity](https://github.com/premacck/Swapi-SDK-revived/blob/main/swapi-sample-components/src/main/java/com/prembros/swapi/sample_components/ui/SwapiListActivity.kt)
