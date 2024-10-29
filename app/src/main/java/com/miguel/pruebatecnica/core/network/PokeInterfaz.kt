package com.miguel.pruebatecnica.core.network

import com.miguel.pruebatecnica.data.models.PokemonResponse
import com.miguel.pruebatecnica.data.models.SinglePokemonResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeInterfaz {
    @GET("pokemon/")
    fun getPokemons(
        @Query("limit") limit: Int?
    ): Call<PokemonResponse>

    @GET("pokemon/{id}/")
    fun getSinglePokemon(
        @Path("id") id: Int
    ): Call<SinglePokemonResponse>
}