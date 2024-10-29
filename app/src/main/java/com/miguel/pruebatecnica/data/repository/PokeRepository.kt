package com.miguel.pruebatecnica.data.repository

import com.miguel.pruebatecnica.data.models.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeRepository(private val pokeRemoteDataSource: PokeRemoteDataSource)