package com.miguel.pruebatecnica.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.pruebatecnica.core.network.PokeInterfaz
import com.miguel.pruebatecnica.core.network.RetrofitHelper
import com.miguel.pruebatecnica.data.models.PokemonObject
import com.miguel.pruebatecnica.data.models.PokemonResponse
import com.miguel.pruebatecnica.data.models.SinglePokemonResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel : ViewModel() {

    private val _pokemonList = MutableLiveData<ArrayList<PokemonObject>>()
    val pokemonList: LiveData<ArrayList<PokemonObject>> = _pokemonList

    var pokemonListTemporal = arrayListOf<PokemonObject>()

    private val pokeEndPoints: PokeInterfaz = RetrofitHelper.getRetrofit()
    private val cantidad = 20

    fun getPokemons() {
        viewModelScope.launch {
            pokeEndPoints.getPokemons(cantidad).enqueue(object : Callback<PokemonResponse> {
                override fun onResponse(
                    p0: Call<PokemonResponse>,
                    response: Response<PokemonResponse>
                ) {
                    val rps = response.body()
                    for (item in rps!!.results){
                        getPokemon(item.url.split("/")[6].toInt(), item.name)
                    }
                }

                override fun onFailure(p0: Call<PokemonResponse>, p1: Throwable) {
                }

            })
        }
    }

    fun getPokemon(id: Int, name: String) {
        viewModelScope.launch {
            pokeEndPoints.getSinglePokemon(id).enqueue(object : Callback<SinglePokemonResponse> {
                override fun onResponse(
                    p0: Call<SinglePokemonResponse>,
                    response: Response<SinglePokemonResponse>
                ) {
                    val rps = response.body()
                    val objetoPoke = PokemonObject(id = id, name = name, foto = rps!!.sprites.front_default)
                    pokemonListTemporal.add(objetoPoke)
                    if (pokemonListTemporal.size == cantidad){
                        _pokemonList.value = pokemonListTemporal
                    }
                }

                override fun onFailure(p0: Call<SinglePokemonResponse>, p1: Throwable) {
                }


            })
        }
    }
}