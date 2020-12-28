package com.prembros.swapi.sample_components.adapter

import android.content.Context
import com.prembros.swapi.sample_components.R
import com.swapi.models.Film

/**
 * Prem's creation, on 28/12/20
 */
class FilmAdapter : BaseRecyclerViewAdapter<Film>() {

  override fun bind(holder: SwapiViewHolder, ctx: Context?, item: Film?, position: Int) {
    holder.run {
      titleLabel.text = ctx?.getString(R.string.title)
      titleText.text = item?.title
      subtitleLabel.text = ctx?.getString(R.string.director)
      subtitleText.text = item?.director
    }
  }
}