package com.prembros.swapi.sample_components

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.prembros.swapi.sample_components.adapter.*
import com.prembros.swapi.sample_components.data.EndlessRecyclerViewScrollListener
import com.prembros.swapi.sample_components.data.onEndlessScroll
import com.swapi.StarWarsSdk
import com.swapi.models.*
import com.swapi.network.RetrofitCallBack
import timber.log.Timber

/**
 * Prem's creation, on 28/12/20
 */
@Suppress("UNCHECKED_CAST") class ListActivity : AppCompatActivity(R.layout.activity_list) {

  private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar_list) }
  private val starWarsList by lazy { findViewById<RecyclerView>(R.id.rv_list) }
  private val loaderProgressBar by lazy { findViewById<ProgressBar>(R.id.pb_loader) }
  private val backgroundImageView by lazy { findViewById<ImageView>(R.id.iv_background) }
  private val listType by lazy { intent?.getIntExtra(LIST_TYPE, LIST_FILMS) }
  private val adapter by lazy {
    when (listType) {
      LIST_FILMS -> FilmAdapter()
      LIST_PEOPLE -> PeopleAdapter()
      LIST_PLANETS -> PlanetAdapter()
      LIST_SPECIES -> SpeciesAdapter()
      LIST_STARSHIPS -> StarshipAdapter()
      LIST_VEHICLES -> VehicleAdapter()
      else -> throw IllegalArgumentException("invalid list type specified")
    }
  }
  private var endlessScrollListener: EndlessRecyclerViewScrollListener? = null
  private var page: Int = 1
  private val failureOrErrorCallback: (Throwable) -> Unit by lazy {
    {
      Timber.e(it)
      loaderProgressBar.visibility = View.GONE
      Snackbar.make(starWarsList, it.message.orEmpty(), Snackbar.LENGTH_LONG)
    }
  }

  companion object {
    private const val LIST_TYPE = "list_type"

    fun launch(from: Context, @SWListType listType: Int) = from.startActivity(Intent(from, ListActivity::class.java).putExtra(LIST_TYPE, listType))
  }

  @Suppress("DEPRECATION")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)
    page = 1
    backgroundImageView.load(randomBackground)
    initToolbar()
    initRecyclerView()
    getData()
  }

  override fun onDestroy() {
    endlessScrollListener?.let { starWarsList.removeOnScrollListener(it) }
    endlessScrollListener = null
    starWarsList.adapter = null
    super.onDestroy()
  }

  private fun initToolbar() {
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

  private fun initRecyclerView() {
    starWarsList.adapter = adapter
    endlessScrollListener = starWarsList.onEndlessScroll { newPage, _ ->
      this.page = newPage
      getData()
    }
  }

  private fun getData() {
    loaderProgressBar.visibility = View.VISIBLE
    when (listType) {
      LIST_FILMS -> StarWarsSdk.repo.getAllFilms(page, retrofitCallBack<Film, FilmAdapter>())
      LIST_PEOPLE -> StarWarsSdk.repo.getAllPeople(page, retrofitCallBack<People, PeopleAdapter>())
      LIST_PLANETS -> StarWarsSdk.repo.getAllPlanets(page, retrofitCallBack<Planet, PlanetAdapter>())
      LIST_SPECIES -> StarWarsSdk.repo.getAllSpecies(page, retrofitCallBack<Species, SpeciesAdapter>())
      LIST_STARSHIPS -> StarWarsSdk.repo.getAllStarships(page, retrofitCallBack<Starship, StarshipAdapter>())
      LIST_VEHICLES -> StarWarsSdk.repo.getAllVehicles(page, retrofitCallBack<Vehicle, VehicleAdapter>())
    }
  }

  private fun <DATA, ADAPTER : BaseRecyclerViewAdapter<DATA>> retrofitCallBack(): RetrofitCallBack<SWList<DATA>>.() -> Unit = {
    onResponse {
      loaderProgressBar.visibility = View.GONE
      (adapter as? ADAPTER)?.addData(it.body()?.results.orEmpty())
    }
    // Called on failed response from server
    onFailure(failureOrErrorCallback)
    // Called when some unexpected error has occurred when executing `onResponse { ... }`
    onError(failureOrErrorCallback)
  }
}