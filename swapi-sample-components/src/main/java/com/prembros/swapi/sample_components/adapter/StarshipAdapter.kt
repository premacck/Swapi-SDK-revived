package com.prembros.swapi.sample_components.adapter

import android.content.Context
import com.prembros.swapi.sample_components.R
import com.swapi.models.Starship

/**
 * Prem's creation, on 28/12/20
 */
class StarshipAdapter : BaseRecyclerViewAdapter<Starship>() {

  override fun bind(holder: SwapiViewHolder, ctx: Context?, item: Starship?, position: Int) {
    holder.run {
      titleLabel.text = ctx?.getString(R.string.name)
      titleText.text = item?.name
      subtitleLabel.text = ctx?.getString(R.string.clazz)
      subtitleText.text = StringBuilder(item?.starshipClass.orEmpty()).append("\nBy ").append(item?.manufacturer.orEmpty())
    }
  }
}