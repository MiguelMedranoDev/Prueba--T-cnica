package com.miguel.pruebatecnica.core.network

import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {

    fun getRetrofit(): PokeInterfaz {
        val rootUrl: HttpUrl = "https://pokeapi.co/api/v2/".toHttpUrlOrNull()!!
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(rootUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(PokeInterfaz::class.java)
    }

}