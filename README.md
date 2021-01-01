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
  <version>release-1.1.2</version>
</dependency>
```

- Add the dependency (for UI components)
```XML
<dependency>
  <groupId>com.github.premacck.Swapi-SDK-revived</groupId>
  <artifactId>swapi-sample-components</artifactId>
  <version>release-1.1.2</version>
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


## Create your own custom sample easily

Using the `swapi-sample-components` dependency, you can create your own UI of SWAPI list in 2 ways:
1. By using the UI in `swapi-sample-components` only
2. By using your own custom implementation (for reference see sample of [State aware Epoxy controller](https://github.com/premacck/Swec))

### 1. By using the in-built UI

- Create an application class and initialize `StarWarsSdk`, as described above.
  - Don't forget to add it in manifest's `<application/>` tag under `android:name` attribute
- Add in-build UI activities to the manifest:
```XML
<application
  ...
  >

  <activity
    android:name="com.prembros.swapi.sample_components.ui.StarWarsMainActivity"
    android:screenOrientation="portrait">

    <intent-filter>
      <action android:name="android.intent.action.MAIN" />

      <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
  </activity>

  <activity
    android:name="com.prembros.swapi.sample_components.ui.StarWarsListActivity"
    android:screenOrientation="portrait" />
</application>
```

### 2. By using your own custom implementation
#### For reference see sample of [State aware Epoxy controller](https://github.com/premacck/Swec)

- Create an application class and initialize `StarWarsSdk`, as described above.
  - Don't forget to add it in manifest's `<application/>` tag under `android:name` attribute
- Add a class extending [SwapiMainActivity](https://github.com/premacck/Swapi-SDK-revived/blob/main/swapi-sample-components/src/main/java/com/prembros/swapi/sample_components/ui/SwapiMainActivity.kt) for your Main page (which will display buttons for all the types of lists that Swapi can display), and override required functions to launch respective list pages:
```kotlin
class StarWarsMainActivity : SwapiMainActivity(R.layout.activity_star_wars_main) {

// you can customize your layout and set listeners in `oncreate()`

  override fun onFilmsClick() {
    StarWarsListActivity.launch(this, LIST_FILMS)
  }

  override fun onPeopleClick() {
    StarWarsListActivity.launch(this, LIST_PEOPLE)
  }

  override fun onPlanetsClick() {
    StarWarsListActivity.launch(this, LIST_PLANETS)
  }

  override fun onSpeciesClick() {
    StarWarsListActivity.launch(this, LIST_SPECIES)
  }

  override fun onStarshipsClick() {
    StarWarsListActivity.launch(this, LIST_STARSHIPS)
  }

  override fun onVehiclesClick() {
    StarWarsListActivity.launch(this, LIST_VEHICLES)
  }
}

```
- Add another class extending the [SwapiListActivity](https://github.com/premacck/Swapi-SDK-revived/blob/main/swapi-sample-components/src/main/java/com/prembros/swapi/sample_components/ui/SwapiListActivity.kt), which will show the list
```kotlin
class StarWarsListActivity : SwapiListActivity(R.layout.activity_star_wars_list) {

// you can customize your layout and set listeners in `oncreate()`
// but remember to initialize the RecyclerView in `initRecyclerView()`

  companion object {
    fun launch(from: Context, @SWListType listType: Int) = from.startActivity(Intent(from, StarWarsListActivity::class.java).putExtra(LIST_TYPE, listType))
  }

  // Initialize the RecyclerView, and pagination here
  override fun initRecyclerView() {
    starWarsList?.adapter = adapter
    endlessScrollListener = starWarsList?.onEndlessScroll { newPage, _ ->
      this.page = newPage
      getStarWarsData()
    }
  }

  override fun <DATA> onDataResponse(it: Response<SWList<DATA>>) {
    // Common callback function, will be called before the dedicated callback functions
    Timber.i("SWAPI list response: $it")
  }

  override fun onAllFilmsResponse(response: Response<SWList<Film>>) {
    // Handle films' list data
  }

  override fun onAllPeopleResponse(response: Response<SWList<People>>) {
    // Handle peoples' list data
  }

  override fun onAllPlanetsResponse(response: Response<SWList<Planet>>) {
    // Handle planets' list data
  }

  override fun onAllSpeciesResponse(response: Response<SWList<Species>>) {
    // Handle species' list data
  }

  override fun onAllStarshipsResponse(response: Response<SWList<Starship>>) {
    // Handle starships' list data
  }

  override fun onAllVehiclesResponse(response: Response<SWList<Vehicle>>) {
    // Handle vehicles' list data
  }

  override fun onDataFailure(throwable: Throwable) {
    // Handle network call failures
  }

  override fun onDataError(throwable: Throwable) {
    // Handle unexpected error failures
  }
}
```

- Do not forget to specify these activities in the manifest
