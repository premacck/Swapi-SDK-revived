package com.swapi.models

import java.util.*

/**
 * Prem's creation, on 28/12/20
 */
interface StarWarsVehicle {
  var name: String
  var model: String
  var vehicleClass: String
  var manufacturer: String
  var costInCredits: String
  var length: String
  var crew: String
  var passengers: String
  var maxAtmospheringSpeed: String
  var cargoCapacity: String
  var consumables: String
  var created: String
  var edited: String
  var url: String
  var pilotsUrls: ArrayList<String>
  var filmsUrls: ArrayList<String>
}