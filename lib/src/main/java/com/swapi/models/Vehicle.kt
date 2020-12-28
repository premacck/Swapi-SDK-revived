package com.swapi.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * Prem's creation, on 28/12/20
 *
 * Vehicle model represents a single transport craft that does not have hyperdrive capability.
 */
@Parcelize data class Vehicle(

  @SerializedName("name") override var name: String,

  @SerializedName("model") override var model: String,

  @SerializedName("vehicle_class") override var vehicleClass: String,

  @SerializedName("manufacturer") override var manufacturer: String,

  @SerializedName("cost_in_credits") override var costInCredits: String,

  @SerializedName("consumables") override var length: String,

  @SerializedName("created") override var crew: String,

  @SerializedName("edited") override var passengers: String,

  @SerializedName("max_atmosphering_speed") override var maxAtmospheringSpeed: String,

  @SerializedName("cargo_capacity") override var cargoCapacity: String,

  @SerializedName("consumables") override var consumables: String,

  @SerializedName("created") override var created: String,

  @SerializedName("edited") override var edited: String,

  @SerializedName("url") override var url: String,

  @SerializedName("pilots") override var pilotsUrls: ArrayList<String>,

  @SerializedName("films") override var filmsUrls: ArrayList<String>,
) : Parcelable, StarWarsVehicle