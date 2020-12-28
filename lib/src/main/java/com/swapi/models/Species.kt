package com.swapi.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * Prem's creation, on 28/12/20
 *
 * Species model represents a type of person or character within the Star Wars Universe.
 */
@Parcelize data class Species(

  @SerializedName("name") var name: String,

  @SerializedName("classification") var classification: String,

  @SerializedName("designation") var designation: String,

  @SerializedName("average_height") var averageHeight: String,

  @SerializedName("average_lifespan") var averageLifespan: String,

  @SerializedName("eye_colors") var eyeColors: String,

  @SerializedName("hair_colors") var hairColors: String,

  @SerializedName("skin_colors") var skinColors: String,

  @SerializedName("homeworld") var homeWorld: String,

  @SerializedName("language") var language: String,

  @SerializedName("created") var created: String,

  @SerializedName("edited") var edited: String,

  @SerializedName("url") var url: String,

  @SerializedName("people") var peopleUrls: ArrayList<String>,

  @SerializedName("films") var filmsUrls: ArrayList<String>,
) : Parcelable