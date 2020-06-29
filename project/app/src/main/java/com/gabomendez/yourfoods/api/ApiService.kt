package com.gabomendez.yourfoods.api

import com.gabomendez.yourfoods.model.*
import com.gabomendez.yourfoods.util.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("api/json/v1/1/random.php")
    fun getRandomFood(): Call<FoodResponse>

    @GET("api/json/v1/1/categories.php")
    fun getCategories(): Call<CategoryResponse>

    @GET("api/json/v1/1/filter.php")
    fun getFoodsByCategory(@Query("c") category: String) : Call<FoodResponse>

    @GET("api/json/v1/1/lookup.php")
    fun getFoodByID(@Query("i") id: String) : Call<FoodResponse>

    //Restaurants end_points
    @Headers("user-key: ${Constants.API_KEY_ZOMATO}")
    @GET("api/v2.1/search")
    fun getRestaurantsByCity(@Query("collection_id") collectionID: String,
                             @Query("entity_id") cityID: Int,
                             @Query("entity_type") type: String = "city") : Call<RestaurantResponse>
}
