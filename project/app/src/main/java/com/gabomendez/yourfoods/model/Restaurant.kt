package com.gabomendez.yourfoods.model

data class RestaurantResponse(
    val restaurants: MutableList<Restaurants>
)

data class Restaurants (
    val restaurant : Restaurant
)

data class Restaurant(
    val id : Int,
    val name : String,
    val url : String,
    val location : Location,
    val cuisines : String,
    val timings : String,
    val highlights : List<String>,
    val thumb : String,
    val user_rating : UserRating,
    val photos_url : String,
    val menu_url : String,
    val featured_image : String,
    val phone_numbers : String,
    val establishment : List<String>
)

data class Location (
    val address : String,
    val locality : String,
    val city : String,
    val city_id : Int,
    val latitude : Double,
    val longitude : Double,
    val zipcode : Int,
    val country_id : Int,
    val locality_verbose : String
)

data class UserRating (
    val aggregate_rating : Double,
    val rating_text : String,
    val rating_color : String,
    val votes : Int
)