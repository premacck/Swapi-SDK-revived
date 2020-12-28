package com.prembros.swapi.sample_components.data

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * Prem's creation, on 28/12/20
 */
fun RecyclerView.onEndlessScroll(onLoadMore: (newPage: Int, totalItemsCount: Int) -> Unit): EndlessRecyclerViewScrollListener {
  val scrollListener = when (layoutManager) {
    null -> throw IllegalArgumentException("LayoutManager must not be null at this stage for RecyclerView: $this")
    is StaggeredGridLayoutManager -> object : EndlessRecyclerViewScrollListener(layoutManager as StaggeredGridLayoutManager) {
      override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) = onLoadMore(page, totalItemsCount)
    }
    is GridLayoutManager -> object : EndlessRecyclerViewScrollListener(layoutManager as GridLayoutManager) {
      override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) = onLoadMore(page, totalItemsCount)
    }
    is LinearLayoutManager -> object : EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
      override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) = onLoadMore(page, totalItemsCount)
    }
    else -> throw IllegalStateException("Only StaggeredGridLayoutManager, GridLayoutManager and LinearLayoutManager are supported in EndlessRecyclerViewScrollListener, your layoutManager is $layoutManager")
  }
  addOnScrollListener(scrollListener)
  return scrollListener
}

abstract class EndlessRecyclerViewScrollListener : RecyclerView.OnScrollListener {
  // The minimum amount of items to have below your current scroll position
  // before loading more.
  private var visibleThreshold = 5

  // The current offset index of data you have loaded
  private var currentPage = 0

  // The total number of items in the data-set after the last load
  private var previousTotalItemCount = 0

  // True if we are still waiting for the last set of data to load.
  private var loading = true

  // Sets the starting page index
  private val startingPageIndex = 0
  var mLayoutManager: RecyclerView.LayoutManager

  constructor(layoutManager: LinearLayoutManager) {
    mLayoutManager = layoutManager
  }

  constructor(layoutManager: GridLayoutManager) {
    mLayoutManager = layoutManager
    visibleThreshold *= layoutManager.spanCount
  }

  constructor(layoutManager: StaggeredGridLayoutManager) {
    mLayoutManager = layoutManager
    visibleThreshold *= layoutManager.spanCount
  }

  fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
    var maxSize = 0
    for (i in lastVisibleItemPositions.indices) {
      if (i == 0) {
        maxSize = lastVisibleItemPositions[i]
      } else if (lastVisibleItemPositions[i] > maxSize) {
        maxSize = lastVisibleItemPositions[i]
      }
    }
    return maxSize
  }

  override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
    var lastVisibleItemPosition = 0
    val totalItemCount = mLayoutManager.itemCount
    when (mLayoutManager) {
      is StaggeredGridLayoutManager -> {
        val lastVisibleItemPositions = (mLayoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
        // get maximum element within the list
        lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
      }
      is GridLayoutManager -> {
        lastVisibleItemPosition = (mLayoutManager as GridLayoutManager).findLastVisibleItemPosition()
      }
      is LinearLayoutManager -> {
        lastVisibleItemPosition = (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()
      }
      else -> throw IllegalStateException("Only StaggeredGridLayoutManager, GridLayoutManager and LinearLayoutManager are supported in EndlessRecyclerViewScrollListener, your layoutManager is $mLayoutManager")
    }

    // If the total item count is zero and the previous isn't, assume the
    // list is invalidated and should be reset back to initial state
    if (totalItemCount < previousTotalItemCount) {
      currentPage = startingPageIndex
      previousTotalItemCount = totalItemCount
      if (totalItemCount == 0) {
        loading = true
      }
    }
    // If it’s still loading, we check to see if the dataset count has
    // changed, if so we conclude it has finished loading and update the current page
    // number and total item count.
    if (loading && totalItemCount > previousTotalItemCount) {
      loading = false
      previousTotalItemCount = totalItemCount
    }

    // If it isn’t currently loading, we check to see if we have breached
    // the visibleThreshold and need to reload more data.
    // If we do need to reload some more data, we execute onLoadMore to fetch the data.
    // threshold should reflect how many total columns there are too
    if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
      currentPage++
      onLoadMore(currentPage, totalItemCount, recyclerView)
      loading = true
    }
  }

  // Call this method whenever performing new searches
  fun resetState() {
    currentPage = startingPageIndex
    previousTotalItemCount = 0
    loading = true
  }

  // Defines the process for actually loading more data based on page
  abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?)
}