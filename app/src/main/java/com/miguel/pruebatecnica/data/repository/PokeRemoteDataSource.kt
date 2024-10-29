package com.miguel.pruebatecnica.data.repository

import com.miguel.pruebatecnica.core.network.RetrofitHelper
import com.miguel.pruebatecnica.data.models.PokemonResponse
import com.miguel.pruebatecnica.data.models.SinglePokemonResponse
import retrofit2.Call
import retrofit2.Response

class PokeRemoteDataSource {

    var serviceCustomer = RetrofitHelper

    suspend fun getPokemons(): Call<PokemonResponse> {
        return serviceCustomer.getRetrofit().getPokemons(20)
    }

    suspend fun getSinglePokemon(id: Int): Call<SinglePokemonResponse>{
        return serviceCustomer.getRetrofit().getSinglePokemon(id)
    }
}