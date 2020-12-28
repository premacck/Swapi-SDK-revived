package com.swapi.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * Prem's creation, on 28/12/20
 *
 * Starship model represents a single transport craft that has hyperdrive capability.
 */
@Parcelize data class Starship(

  @SerializedName("starship_class") var starshipClass: String,

  @SerializedName("hyperdrive_rating") var hyperdriveRating: String,

  @SerializedName("MGLT") var mglt: String,

  @SerializedName("name") var name: String,

  @SerializedName("model") var model: String,

  @SerializedName("vehicle_class") var vehicleClass: String,

  @SerializedName("manufacturer") var manufacturer: String,

  @SerializedName("cost_in_credits") var costInCredits: String,

  @SerializedName("length") var length: String,

  @SerializedName("crew") var crew: String,

  @SerializedName("passengers") var passengers: String,

  @SerializedName("max_atmosphering_speed") var maxAtmospheringSpeed: String,

  @SerializedName("cargo_capacity") var cargoCapacity: String,

  @SerializedName("consumables") var consumables: String,

  @SerializedName("created") var created: String,

  @SerializedName("edited") var edited: String,

  @SerializedName("url") var url: String,

  @SerializedName("pilots") var pilotsUrls: ArrayList<String>,

  @SerializedName("films") var filmsUrls: ArrayList<String>,
) : Parcelable