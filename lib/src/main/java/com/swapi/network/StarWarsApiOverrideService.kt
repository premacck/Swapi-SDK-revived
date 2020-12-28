package com.swapi.network

import com.swapi.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Prem's creation, on 28/12/20
 *
 * Star Wars API interface for making network calls.
 */
interface StarWarsApiOverrideService {

  @GET fun getRootUrls(@Url url: String = "https://swapi.dev/api/"): Call<Root>

  @GET fun getAllPeople(@Url url: String = "https://swapi.dev/api/people/", @Query("page") page: Int): Call<SWList<People>>

  @GET fun getPeople(@Url url: String = "https://swapi.dev/api/people/{id}/", @Path("id") peopleId: Int): Call<People>

  @GET fun getAllFilms(@Url url: String = "https://swapi.dev/api/films/", @Query("page") page: Int): Call<SWList<Film>>

  @GET fun getFilm(@Url url: String = "https://swapi.dev/api/films/{id}/", @Path("id") filmId: Int): Call<Film>

  @GET fun getAllStarships(@Url url: String = "https://swapi.dev/api/starships/", @Query("page") page: Int): Call<SWList<Starship>>

  @GET fun getStarship(@Url url: String = "https://swapi.dev/api/starships/{id}/", @Path("id") starshipId: Int): Call<Starship>

  @GET fun getAllVehicles(@Url url: String = "https://swapi.dev/api/vehicles/", @Query("page") page: Int): Call<SWList<Vehicle>>

  @GET fun getVehicle(@Url url: String = "https://swapi.dev/api/vehicles/{id}/", @Path("id") vehicleId: Int): Call<Vehicle>

  @GET fun getAllSpecies(@Url url: String = "https://swapi.dev/api/species/", @Query("page") page: Int): Call<SWList<Species>>

  @GET fun getSpecies(@Url url: String = "https://swapi.dev/api/species/{id}/", @Path("id") speciesId: Int): Call<Species>

  @GET fun getAllPlanets(@Url url: String = "https://swapi.dev/api/planets/", @Query("page") page: Int): Call<SWList<Planet>>

  @GET fun getPlanet(@Url url: String = "https://swapi.dev/api/planets/{id}/", @Path("id") planetId: Int): Call<Planet>
}