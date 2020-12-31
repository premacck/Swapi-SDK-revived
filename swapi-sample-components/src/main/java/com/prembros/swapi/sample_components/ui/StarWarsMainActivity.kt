package com.prembros.swapi.sample_components.ui

import com.prembros.swapi.sample_components.*

class StarWarsMainActivity : SwapiMainActivity(R.layout.activity_star_wars_main) {

  override fun onFilmsClick() {
    StarWarsListActivity.launch(this, LIST_FILMS)
  }

  override fun onPeopleClick() {
    StarWarsListActivity.launch(this, LIST_PEOPLE)
  }

  override fun onPlanetsClick() {
    StarWarsListActivity.launch(this, LIST_PLANETS)
  }

  override fun onSpeciesClick() {
    StarWarsListActivity.launch(this, LIST_SPECIES)
  }

  override fun onStarshipsClick() {
    StarWarsListActivity.launch(this, LIST_STARSHIPS)
  }

  override fun onVehiclesClick() {
    StarWarsListActivity.launch(this, LIST_VEHICLES)
  }
}