package com.gabomendez.yourfoods.api

import com.gabomendez.yourfoods.model.*
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/json/v1/1/random.php")
    fun getRandomFood(): Call<FoodResponse>

    @GET("api/json/v1/1/categories.php")
    fun getCategories(): Call<CategoryResponse>
}
