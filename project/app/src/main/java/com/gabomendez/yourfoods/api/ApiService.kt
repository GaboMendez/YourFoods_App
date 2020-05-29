package com.gabomendez.yourfoods.api

import com.gabomendez.yourfoods.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/json/v1/1/random.php")
    fun getRandomFood(): Call<FoodResponse>

    @GET("api/json/v1/1/categories.php")
    fun getCategories(): Call<CategoryResponse>

    @GET("api/json/v1/1/filter.php")
    fun getFoodByCategory(@Query("c") category: String) : Call<FoodResponse>
}
