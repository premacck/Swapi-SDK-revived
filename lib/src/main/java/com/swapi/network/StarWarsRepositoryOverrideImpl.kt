package com.swapi.network

import com.swapi.models.*

class StarWarsRepositoryOverrideImpl(private val service: StarWarsApiOverrideService) : StarWarsRepository {

  override fun getRootUrls(callback: RetrofitCallBack<Root>.() -> Unit) {
    service.getRootUrls().callApi(callback)
  }

  override fun getAllPeople(page: Int, callback: RetrofitCallBack<SWList<People>>.() -> Unit) {
    service.getAllPeople(page = page).callApi(callback)
  }

  override fun getPeople(peopleId: Int, callback: RetrofitCallBack<People>.() -> Unit) {
    service.getPeople(peopleId = peopleId).callApi(callback)
  }

  override fun getAllFilms(page: Int, callback: RetrofitCallBack<SWList<Film>>.() -> Unit) {
    service.getAllFilms(page = page).callApi(callback)
  }

  override fun getFilm(filmId: Int, callback: RetrofitCallBack<Film>.() -> Unit) {
    service.getFilm(filmId = filmId).callApi(callback)
  }

  override fun getAllStarships(page: Int, callback: RetrofitCallBack<SWList<Starship>>.() -> Unit) {
    service.getAllStarships(page = page).callApi(callback)
  }

  override fun getStarship(starshipId: Int, callback: RetrofitCallBack<Starship>.() -> Unit) {
    service.getStarship(starshipId = starshipId).callApi(callback)
  }

  override fun getAllVehicles(page: Int, callback: RetrofitCallBack<SWList<Vehicle>>.() -> Unit) {
    service.getAllVehicles(page = page).callApi(callback)
  }

  override fun getVehicle(vehicleId: Int, callback: RetrofitCallBack<Vehicle>.() -> Unit) {
    service.getVehicle(vehicleId = vehicleId).callApi(callback)
  }

  override fun getAllSpecies(page: Int, callback: RetrofitCallBack<SWList<Species>>.() -> Unit) {
    service.getAllSpecies(page = page).callApi(callback)
  }

  override fun getSpecies(speciesId: Int, callback: RetrofitCallBack<Species>.() -> Unit) {
    service.getSpecies(speciesId = speciesId).callApi(callback)
  }

  override fun getAllPlanets(page: Int, callback: RetrofitCallBack<SWList<Planet>>.() -> Unit) {
    service.getAllPlanets(page = page).callApi(callback)
  }

  override fun getPlanet(planetId: Int, callback: RetrofitCallBack<Planet>.() -> Unit) {
    service.getPlanet(planetId = planetId).callApi(callback)
  }
}