package com.swapi.models

import java.io.Serializable
import java.util.*

/**
 * Prem's creation, on 28/12/20
 *
 * Generic list model
 */
data class SWList<T>(
  val count: Int = 0,
  val next: String? = null,
  val previous: String? = null,
  val results: ArrayList<T>? = null,
) : Serializable {

  val hasMore: Boolean
    get() = !next.isNullOrBlank()
}