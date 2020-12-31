package com.prembros.swapi.sample_components.adapter

import android.content.Context
import com.prembros.swapi.sample_components.R
import com.swapi.models.Vehicle

/**
 * Prem's creation, on 28/12/20
 */
class VehicleAdapter : BaseRecyclerViewAdapter<Vehicle>() {

  override fun bind(holder: SwapiViewHolder, ctx: Context?, item: Vehicle?, position: Int) {
    holder.run {
      titleLabel.text = ctx?.getString(R.string.name)
      titleText.text = StringBuilder(item?.name.orEmpty()).append(" - ").append(item?.model.orEmpty())
      subtitleLabel.text = ctx?.getString(R.string.manufacturer)
      subtitleText.text = item?.manufacturer
    }
  }
}