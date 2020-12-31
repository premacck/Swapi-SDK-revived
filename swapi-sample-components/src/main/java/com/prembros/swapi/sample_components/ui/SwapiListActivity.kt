package com.prembros.swapi.sample_components.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.prembros.swapi.sample_components.*
import com.prembros.swapi.sample_components.adapter.*
import com.prembros.swapi.sample_components.data.EndlessRecyclerViewScrollListener
import com.swapi.StarWarsSdk
import com.swapi.models.*
import com.swapi.network.RetrofitCallBack
import retrofit2.Response
import timber.log.Timber

/**
 * Prem's creation, on 28/12/20
 */
abstract class SwapiListActivity(@LayoutRes contentLayoutId: Int = R.layout.activity_star_wars_list) : AppCompatActivity(contentLayoutId) {

  private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar_star_wars_list) }
  private val backgroundImageView by lazy { findViewById<ImageView>(R.id.iv_star_wars_background) }
  open val starWarsList: RecyclerView? by lazy { findViewById(R.id.rv_star_wars_list) }
  open val loaderProgressBar: ProgressBar? by lazy { findViewById(R.id.pb_star_wars_loader) }

  protected val listType by lazy { intent?.getIntExtra(LIST_TYPE, LIST_FILMS) }
  internal val adapter by lazy {
    when (listType) {
      LIST_FILMS -> FilmAdapter()
      LIST_PEOPLE -> PeopleAdapter()
      LIST_PLANETS -> PlanetAdapter()
      LIST_SPECIES -> SpeciesAdapter()
      LIST_STARSHIPS -> StarshipAdapter()
      LIST_VEHICLES -> VehicleAdapter()
      else -> throw IllegalArgumentException("invalid list type specified. You must specify a valid LIST_TYPE from @SWListType")
    }
  }
  protected var endlessScrollListener: EndlessRecyclerViewScrollListener? = null
  protected var page: Int = 1

  companion object {
    const val LIST_TYPE = "list_type"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)
    page = 1
    loadBackgroundImage(backgroundImageView)
    initToolbar(toolbar)
    initRecyclerView()
    getStarWarsData()
  }

  open fun loadBackgroundImage(backgroundImageView: ImageView?) {
    backgroundImageView?.load(randomBackground)
  }

  override fun onDestroy() {
    endlessScrollListener?.let { starWarsList?.removeOnScrollListener(it) }
    endlessScrollListener = null
    starWarsList?.adapter = null
    super.onDestroy()
  }

  open fun initToolbar(toolbar: Toolbar?) {
    toolbar?.setNavigationOnClickListener { onBackPressed() }
    toolbar?.title = getString(
      when (listType) {
        LIST_FILMS -> R.string.films
        LIST_PEOPLE -> R.string.people
        LIST_PLANETS -> R.string.planets
        LIST_SPECIES -> R.string.species
        LIST_STARSHIPS -> R.string.starships
        LIST_VEHICLES -> R.string.vehicles
        else -> R.string.app_name
      }
    )
  }

  fun getStarWarsData() {
    loaderProgressBar?.visibility = View.VISIBLE
    when (listType) {
      LIST_FILMS -> StarWarsSdk.repo.getAllFilms(page, retrofitCallBack())
      LIST_PEOPLE -> StarWarsSdk.repo.getAllPeople(page, retrofitCallBack())
      LIST_PLANETS -> StarWarsSdk.repo.getAllPlanets(page, retrofitCallBack())
      LIST_SPECIES -> StarWarsSdk.repo.getAllSpecies(page, retrofitCallBack())
      LIST_STARSHIPS -> StarWarsSdk.repo.getAllStarships(page, retrofitCallBack())
      LIST_VEHICLES -> StarWarsSdk.repo.getAllVehicles(page, retrofitCallBack())
    }
  }

  @Suppress("UNCHECKED_CAST")
  private fun <DATA> retrofitCallBack(): RetrofitCallBack<SWList<DATA>>.() -> Unit = {
    onResponse {
      loaderProgressBar?.visibility = View.GONE
      onDataResponse(it)
      when (listType) {
        LIST_FILMS -> onAllFilmsResponse(it as Response<SWList<Film>>)
        LIST_PEOPLE -> onAllPeopleResponse(it as Response<SWList<People>>)
        LIST_PLANETS -> onAllPlanetsResponse(it as Response<SWList<Planet>>)
        LIST_SPECIES -> onAllSpeciesResponse(it as Response<SWList<Species>>)
        LIST_STARSHIPS -> onAllStarshipsResponse(it as Response<SWList<Starship>>)
        LIST_VEHICLES -> onAllVehiclesResponse(it as Response<SWList<Vehicle>>)
      }
    }
    // Called on failed response from server
    onFailure {
      loaderProgressBar?.visibility = View.GONE
      onDataFailure(it)
    }
    // Called when some unexpected error has occurred when executing `onResponse { ... }`
    onError {
      loaderProgressBar?.visibility = View.GONE
      onDataError(it)
    }
  }

  abstract fun initRecyclerView()

  /**
   * Common callback for all Swapi responses
   */
  abstract fun <DATA> onDataResponse(it: Response<SWList<DATA>>)

  /**
   * Dedicated callback for all [Film] response
   */
  open fun onAllFilmsResponse(response: Response<SWList<Film>>) {}

  /**
   * Dedicated callback for all [People] response
   */
  open fun onAllPeopleResponse(response: Response<SWList<People>>) {}

  /**
   * Dedicated callback for all [Planet] response
   */
  open fun onAllPlanetsResponse(response: Response<SWList<Planet>>) {}

  /**
   * Dedicated callback for all [Species] response
   */
  open fun onAllSpeciesResponse(response: Response<SWList<Species>>) {}

  /**
   * Dedicated callback for all [Starship] response
   */
  open fun onAllStarshipsResponse(response: Response<SWList<Starship>>) {}

  /**
   * Dedicated callback for all [Vehicle] response
   */
  open fun onAllVehiclesResponse(response: Response<SWList<Vehicle>>) {}

  open fun onDataFailure(throwable: Throwable) {
    Timber.e(throwable)
  }

  open fun onDataError(throwable: Throwable) {
    Timber.e(throwable)
  }
}