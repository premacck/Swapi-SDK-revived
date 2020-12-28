package com.prembros.swapi.sample_components

import androidx.annotation.IntDef
import kotlin.random.Random

/**
 * Prem's creation, on 28/12/20
 */
const val LIST_FILMS = 0
const val LIST_PEOPLE = 1
const val LIST_PLANETS = 2
const val LIST_SPECIES = 3
const val LIST_STARSHIPS = 4
const val LIST_VEHICLES = 5

@Retention @IntDef(LIST_FILMS, LIST_PEOPLE, LIST_PLANETS, LIST_SPECIES, LIST_STARSHIPS, LIST_VEHICLES) annotation class SWListType

private val backgrounds = arrayOf(
  R.drawable.bg_1, R.drawable.bg_2, R.drawable.bg_3, R.drawable.bg_4, R.drawable.bg_5, R.drawable.bg_6, R.drawable.bg_7, R.drawable.bg_8, R.drawable.bg_9, R.drawable.bg_10
)

private val random = Random(backgrounds.size)

val randomBackground
  get() = backgrounds[random.nextInt(backgrounds.size)]