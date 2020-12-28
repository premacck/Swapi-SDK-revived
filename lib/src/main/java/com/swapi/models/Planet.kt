package com.swapi.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * Prem's creation, on 28/12/20
 *
 * data class representing a large mass, planet or planetoid in the Star Wars Universe, at the time of 0 ABY.
 */
@Parcelize data class Planet(

  @SerializedName("name") var name: String,

  @SerializedName("diameter") var diameter: String,

  @SerializedName("gravity") var gravity: String,

  @SerializedName("population") var population: String,

  @SerializedName("climate") var climate: String,

  @SerializedName("terrain") var terrain: String,

  @SerializedName("created") var created: String,

  @SerializedName("edited") var edited: String,

  @SerializedName("url") var url: String,

  @SerializedName("rotation_period") var rotationPeriod: String,

  @SerializedName("orbital_period") var orbitalPeriod: String,

  @SerializedName("surface_water") var surfaceWater: String,

  @SerializedName("residents") var residentsUrls: ArrayList<String>,

  @SerializedName("films") var filmsUrls: ArrayList<String>,
) : Parcelable