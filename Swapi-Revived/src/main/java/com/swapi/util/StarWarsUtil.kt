package com.swapi.util

import com.swapi.models.People
import com.swapi.models.Vehicle
import java.util.*

/**
 * Prem's creation, on 28/12/20
 *
 * Utility class for Star Wars API
 */
object StarWarsUtil {
  private fun compare(lhs: Int, rhs: Int): Int {
    return if (lhs < rhs) -1 else if (lhs == rhs) 0 else 1
  }

  fun sortPeopleByMass(peopleList: ArrayList<People>?, @SWSort sortBy: Int): ArrayList<People>? = peopleList?.apply {
    sortWith { lhs, rhs ->
      val heightLhs = lhs.mass.toInt()
      val heightRhs = rhs.mass.toInt()
      when (sortBy) {
        ASC -> compare(heightLhs, heightRhs)
        DESC -> compare(heightRhs, heightLhs)
        else -> throw IllegalArgumentException("sortBy argument must be one of ASC or DESC")
      }
    }
  }

  fun sortPeopleByHeight(peopleList: ArrayList<People>?, @SWSort sortBy: Int): ArrayList<People>? = peopleList?.apply {
    sortWith { lhs, rhs ->
      val heightLhs = lhs.height.toInt()
      val heightRhs = rhs.height.toInt()
      when (sortBy) {
        ASC -> compare(heightLhs, heightRhs)
        DESC -> compare(heightRhs, heightLhs)
        else -> throw IllegalArgumentException("sortBy argument must be one of ASC or DESC")
      }
    }
  }

  fun isEmpireVehicle(vehicle: Vehicle?): Boolean = if (vehicle == null || vehicle.manufacturer.isBlank()) {
    false
  } else {
    vehicle.manufacturer.isOneOf("Sienar", "Kuat", "Imperial", "Aratech")
  }

  fun filmUrlToFilmTitle(filmUrl: String): String? {
    return try {
      when (filmUrl[filmUrl.length - 2].toInt()) {
        1 -> "Star Wars Episode IV A New Hope"
        2 -> "Star Wars Episode V The Empire Strikes Back"
        3 -> "Star Wars Episode VI Return of the Jedi"
        4 -> "Star Wars Episode I The Phantom Menace"
        5 -> "Star Wars Episode II Attack of the Clones"
        6 -> "Star Wars Episode III Revenge of the Sith"
        else -> null
      }
    } catch (e: Exception) {
      e.printStackTrace()
      null
    }
  }

  private fun <T> T.isOneOf(vararg otherNumbers: T): Boolean {
    val others = hashSetOf(*otherNumbers)
    val result = this in others
    others.clear()
    return result
  }
}