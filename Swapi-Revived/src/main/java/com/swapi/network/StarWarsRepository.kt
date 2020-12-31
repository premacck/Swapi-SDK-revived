package com.swapi.network

import com.swapi.models.*

/**
 * Prem's creation, on 28/12/20
 */
interface StarWarsRepository {

  fun getRootUrls(callback: RetrofitCallBack<Root>.() -> Unit)

  fun getAllPeople(page: Int, callback: RetrofitCallBack<SWList<People>>.() -> Unit)

  fun getPeople(peopleId: Int, callback: RetrofitCallBack<People>.() -> Unit)

  fun getAllFilms(page: Int, callback: RetrofitCallBack<SWList<Film>>.() -> Unit)

  fun getFilm(filmId: Int, callback: RetrofitCallBack<Film>.() -> Unit)

  fun getAllStarships(page: Int, callback: RetrofitCallBack<SWList<Starship>>.() -> Unit)

  fun getStarship(starshipId: Int, callback: RetrofitCallBack<Starship>.() -> Unit)

  fun getAllVehicles(page: Int, callback: RetrofitCallBack<SWList<Vehicle>>.() -> Unit)

  fun getVehicle(vehicleId: Int, callback: RetrofitCallBack<Vehicle>.() -> Unit)

  fun getAllSpecies(page: Int, callback: RetrofitCallBack<SWList<Species>>.() -> Unit)

  fun getSpecies(speciesId: Int, callback: RetrofitCallBack<Species>.() -> Unit)

  fun getAllPlanets(page: Int, callback: RetrofitCallBack<SWList<Planet>>.() -> Unit)

  fun getPlanet(planetId: Int, callback: RetrofitCallBack<Planet>.() -> Unit)
}