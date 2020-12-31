package com.prembros.swapi.sample_components.adapter

import android.content.Context
import com.prembros.swapi.sample_components.R
import com.swapi.models.Planet

/**
 * Prem's creation, on 28/12/20
 */
class PlanetAdapter : BaseRecyclerViewAdapter<Planet>() {

  override fun bind(holder: SwapiViewHolder, ctx: Context?, item: Planet?, position: Int) {
    holder.run {
      titleLabel.text = ctx?.getString(R.string.name)
      titleText.text = item?.name
      subtitleLabel.text = ctx?.getString(R.string.conditions)
      subtitleText.text = StringBuilder(item?.terrain.orEmpty()).append("; ").append(item?.climate.orEmpty())
    }
  }
}