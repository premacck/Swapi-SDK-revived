package com.prembros.swapi.sample_components.adapter

import android.content.Context
import com.prembros.swapi.sample_components.R
import com.swapi.models.Species

/**
 * Prem's creation, on 28/12/20
 */
class SpeciesAdapter : BaseRecyclerViewAdapter<Species>() {

  override fun bind(holder: SwapiViewHolder, ctx: Context?, item: Species?, position: Int) {
    holder.run {
      titleLabel.text = ctx?.getString(R.string.name)
      titleText.text = item?.name
      subtitleLabel.text = ctx?.getString(R.string.classification)
      subtitleText.text = StringBuilder(item?.classification.orEmpty()).append("\nSpeaks ").append(item?.language.orEmpty())
    }
  }
}