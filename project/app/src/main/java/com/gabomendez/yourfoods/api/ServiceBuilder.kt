package com.gabomendez.yourfoods.api

import com.gabomendez.yourfoods.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()

    private val retrofitMeal = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL_MEAL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val retrofitRestaurant = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL_RESTAURANT)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>, baseURL: String): T{
        return when(baseURL){
            "Meal" -> retrofitMeal.create(service)
            "Restaurant" -> retrofitRestaurant.create(service)
            else -> retrofitMeal.create(service)
        }
    }
}