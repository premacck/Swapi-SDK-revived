package com.swapi.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * Prem's creation, on 28/12/20
 *
 * data class representing a Star Wars single film.
 */
@Parcelize data class Film(
  @SerializedName("title") val title: String,

  @SerializedName("episode_id") val episodeId: Int = 0,

  @SerializedName("opening_crawl") val openingCrawl: String,

  @SerializedName("director") val director: String,

  @SerializedName("producer") val producer: String,

  @SerializedName("url") val url: String,

  @SerializedName("created") val created: String,

  @SerializedName("edited") val edited: String,

  @SerializedName("species") val speciesUrls: ArrayList<String>,

  @SerializedName("starships") val starshipsUrls: ArrayList<String>,

  @SerializedName("vehicles") val vehiclesUrls: ArrayList<String>,

  @SerializedName("planets") val planetsUrls: ArrayList<String>,

  @SerializedName("characters") val charactersUrls: ArrayList<String>,
) : Parcelable