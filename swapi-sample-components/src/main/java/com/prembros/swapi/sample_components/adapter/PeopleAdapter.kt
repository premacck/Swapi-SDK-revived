package com.prembros.swapi.sample_components.adapter

import android.content.Context
import com.prembros.swapi.sample_components.R
import com.swapi.models.People

/**
 * Prem's creation, on 28/12/20
 */
class PeopleAdapter : BaseRecyclerViewAdapter<People>() {

  override fun bind(holder: SwapiViewHolder, ctx: Context?, item: People?, position: Int) {
    holder.run {
      titleLabel.text = ctx?.getString(R.string.name)
      titleText.text = item?.name
      subtitleLabel.text = ctx?.getString(R.string.birth_year)
      subtitleText.text = item?.birthYear
    }
  }
}