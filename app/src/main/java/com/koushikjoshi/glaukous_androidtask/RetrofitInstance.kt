package com.koushikjoshi.glaukous_androidtask

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    val api: TodoApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://ptl-stageapi.manarsh.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TodoApi::class.java)
    }

}