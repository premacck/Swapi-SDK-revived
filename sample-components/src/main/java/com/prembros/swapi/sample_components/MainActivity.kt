package com.prembros.swapi.sample_components

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

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
    btnFilms?.setOnClickListener { ListActivity.launch(this, LIST_FILMS) }
    btnPeople?.setOnClickListener { ListActivity.launch(this, LIST_PEOPLE) }
    btnPlanets?.setOnClickListener { ListActivity.launch(this, LIST_PLANETS) }
    btnSpecies?.setOnClickListener { ListActivity.launch(this, LIST_SPECIES) }
    btnStarships?.setOnClickListener { ListActivity.launch(this, LIST_STARSHIPS) }
    btnVehicles?.setOnClickListener { ListActivity.launch(this, LIST_VEHICLES) }
  }
}