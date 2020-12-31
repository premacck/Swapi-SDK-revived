package com.prembros.swapi.sample_components.ui

import android.os.Bundle
import android.widget.Button
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.prembros.swapi.sample_components.R

abstract class SwapiMainActivity(@LayoutRes contentLayoutId: Int = R.layout.activity_star_wars_main) : AppCompatActivity(contentLayoutId) {

  private val btnFilms by lazy { findViewById<Button>(R.id.btn_films) }
  private val btnPeople by lazy { findViewById<Button>(R.id.btn_people) }
  private val btnPlanets by lazy { findViewById<Button>(R.id.btn_planets) }
  private val btnSpecies by lazy { findViewById<Button>(R.id.btn_species) }
  private val btnStarships by lazy { findViewById<Button>(R.id.btn_starships) }
  private val btnVehicles by lazy { findViewById<Button>(R.id.btn_vehicles) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initListeners()
  }

  private fun initListeners() {
    btnFilms?.setOnClickListener { onFilmsClick() }
    btnPeople?.setOnClickListener { onPeopleClick() }
    btnPlanets?.setOnClickListener { onPlanetsClick() }
    btnSpecies?.setOnClickListener { onSpeciesClick() }
    btnStarships?.setOnClickListener { onStarshipsClick() }
    btnVehicles?.setOnClickListener { onVehiclesClick() }
  }

  abstract fun onFilmsClick()

  abstract fun onPeopleClick()

  abstract fun onPlanetsClick()

  abstract fun onSpeciesClick()

  abstract fun onStarshipsClick()

  abstract fun onVehiclesClick()
}