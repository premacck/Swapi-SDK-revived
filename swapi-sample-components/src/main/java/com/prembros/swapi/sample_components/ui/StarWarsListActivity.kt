package com.prembros.swapi.sample_components.ui

import android.content.Context
import android.content.Intent
import com.google.android.material.snackbar.Snackbar
import com.prembros.swapi.sample_components.SWListType
import com.prembros.swapi.sample_components.adapter.BaseRecyclerViewAdapter
import com.prembros.swapi.sample_components.data.onEndlessScroll
import com.swapi.models.SWList
import retrofit2.Response
import timber.log.Timber

/**
 * Prem's creation, on 29/12/20
 *
 * Sample Implementation Activity of [SwapiListActivity], to get and display a list of specific Star Wars data by specifying [SWListType] during launch
 */
class StarWarsListActivity : SwapiListActivity() {

  companion object {
    fun launch(from: Context, @SWListType listType: Int) = from.startActivity(Intent(from, StarWarsListActivity::class.java).putExtra(LIST_TYPE, listType))
  }

  override fun initRecyclerView() {
    starWarsList?.adapter = adapter
    endlessScrollListener = starWarsList?.onEndlessScroll { newPage, _ ->
      this.page = newPage
      getStarWarsData()
    }
  }

  @Suppress("UNCHECKED_CAST") override fun <DATA> onDataResponse(it: Response<SWList<DATA>>) {
    (adapter as? BaseRecyclerViewAdapter<DATA>)?.addData(it.body()?.results.orEmpty())
  }

  override fun onDataFailure(throwable: Throwable) = onFailure(throwable)

  override fun onDataError(throwable: Throwable) = onFailure(throwable)

  private fun onFailure(throwable: Throwable) {
    Timber.e(throwable)
    starWarsList?.let { Snackbar.make(it, throwable.message.orEmpty(), Snackbar.LENGTH_LONG) }
  }
}