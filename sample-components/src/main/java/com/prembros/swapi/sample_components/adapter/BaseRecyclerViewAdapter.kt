package com.prembros.swapi.sample_components.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prembros.swapi.sample_components.R

/**
 * Prem's creation, on 28/12/20
 */
abstract class BaseRecyclerViewAdapter<DATA> : RecyclerView.Adapter<BaseRecyclerViewAdapter.SwapiViewHolder>() {

  var currentData: MutableList<DATA> = mutableListOf()

  override fun getItemCount(): Int = currentData.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwapiViewHolder = SwapiViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
  )

  abstract fun bind(holder: SwapiViewHolder, ctx: Context?, item: DATA?, position: Int)

  override fun onBindViewHolder(holder: SwapiViewHolder, position: Int) = bind(holder, holder.itemView.context, currentData.getOrNull(position), holder.adapterPosition)

  fun setData(data: List<DATA>) {
    currentData = data.toMutableList()
    notifyDataSetChanged()
  }

  fun addData(data: List<DATA>) {
    currentData.addAll(data)
    notifyItemRangeInserted(currentData.size, data.size)
  }

  class SwapiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titleLabel by lazy { view.findViewById<TextView>(R.id.tv_title_label) }
    val titleText by lazy { view.findViewById<TextView>(R.id.tv_title) }
    val subtitleLabel by lazy { view.findViewById<TextView>(R.id.tv_subtitle_label) }
    val subtitleText by lazy { view.findViewById<TextView>(R.id.tv_subtitle) }
  }
}