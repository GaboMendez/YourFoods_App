package com.gabomendez.yourfoods.model

data class RestaurantResponse(
    val restaurants: MutableList<Restaurant>
)

data class Restaurant(
    val id : Int,
    val name : String,
    val address : String,
    val city : String,
    val state : String,
    val area : String,
    val postal_code : Int,
    val country : String,
    val phone : Int,
    val lat : Double,
    val lng : Double,
    val price : Int,
    val reserve_url : String,
    val mobile_reserve_url : String,
    val image_url : String
)