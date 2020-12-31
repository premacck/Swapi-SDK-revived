package com.prembros.swapi.sample_components.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.prembros.swapi.sample_components.*

open class StarWarsMainActivity : AppCompatActivity(R.layout.activity_star_wars_main) {

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
    btnFilms?.setOnClickListener { StarWarsListActivity.launch(this, LIST_FILMS) }
    btnPeople?.setOnClickListener { StarWarsListActivity.launch(this, LIST_PEOPLE) }
    btnPlanets?.setOnClickListener { StarWarsListActivity.launch(this, LIST_PLANETS) }
    btnSpecies?.setOnClickListener { StarWarsListActivity.launch(this, LIST_SPECIES) }
    btnStarships?.setOnClickListener { StarWarsListActivity.launch(this, LIST_STARSHIPS) }
    btnVehicles?.setOnClickListener { StarWarsListActivity.launch(this, LIST_VEHICLES) }
  }
}