package com.swapi.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Prem's creation, on 28/12/20
 *
 * Root model that provides information on all available resources within the API.
 */
@Parcelize data class Root(

  @SerializedName("films") var filmsUrl: String,

  @SerializedName("people") var peopleUrl: String,

  @SerializedName("planets") var planetsUrl: String,

  @SerializedName("species") var speciesUrl: String,

  @SerializedName("starships") var starshipsUrl: String,

  @SerializedName("vehicles") var vehiclesUrl: String,
) : Parcelable