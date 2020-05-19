package com.gabomendez.yourfoods.api

import com.gabomendez.yourfoods.model.FoodResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/json/v1/1/random.php")
    fun getRandomFood(): Call<FoodResponse>
}
