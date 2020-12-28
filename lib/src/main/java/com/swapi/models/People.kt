package com.swapi.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * Prem's creation, on 28/12/20
 *
 * data class representing an individual person or character within the Star Wars universe.
 */
@Parcelize data class People(
  @SerializedName("name") var name: String,

  @SerializedName("birth_year") var birthYear: String,

  @SerializedName("gender") var gender: String,

  @SerializedName("hair_color") var hairColor: String,

  @SerializedName("height") var height: String,

  @SerializedName("homeworld") var homeWorldUrl: String,

  @SerializedName("mass") var mass: String,

  @SerializedName("skin_color") var skinColor: String,

  @SerializedName("created") var created: String,

  @SerializedName("edited") var edited: String,

  @SerializedName("url") var url: String,

  @SerializedName("films") var filmsUrls: ArrayList<String>,

  @SerializedName("species") var speciesUrls: ArrayList<String>,

  @SerializedName("starships") var starshipsUrls: ArrayList<String>,

  @SerializedName("vehicles") var vehiclesUrls: ArrayList<String>,
) : Parcelable