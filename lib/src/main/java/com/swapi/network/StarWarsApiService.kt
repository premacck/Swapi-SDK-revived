package com.swapi.network

import com.swapi.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Prem's creation, on 28/12/20
 *
 * Star Wars API interface for making network calls.
 */
interface StarWarsApiService {

  @GET("/") fun getRootUrls(): Call<Root>

  @GET("/people/") fun getAllPeople(@Query("page") page: Int): Call<SWList<People>>

  @GET("/people/{id}/") fun getPeople(@Path("id") peopleId: Int): Call<People>

  @GET("/films/") fun getAllFilms(@Query("page") page: Int): Call<SWList<Film>>

  @GET("/films/{id}/") fun getFilm(@Path("id") filmId: Int): Call<Film>

  @GET("/starships") fun getAllStarships(@Query("page") page: Int): Call<SWList<Starship>>

  @GET("/starships/{id}/") fun getStarship(@Path("id") starshipId: Int): Call<Starship>

  @GET("/vehicles/") fun getAllVehicles(@Query("page") page: Int): Call<SWList<Vehicle>>

  @GET("/vehicles/{id}/") fun getVehicle(@Path("id") vehicleId: Int): Call<Vehicle>

  @GET("/species/") fun getAllSpecies(@Query("page") page: Int): Call<SWList<Species>>

  @GET("/species/{id}/") fun getSpecies(@Path("id") speciesId: Int): Call<Species>

  @GET("/planets/") fun getAllPlanets(@Query("page") page: Int): Call<SWList<Planet>>

  @GET("/planets/{id}/") fun getPlanet(@Path("id") planetId: Int): Call<Planet>
}