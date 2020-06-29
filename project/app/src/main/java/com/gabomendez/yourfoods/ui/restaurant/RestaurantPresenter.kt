package com.gabomendez.yourfoods.ui.restaurant

import com.gabomendez.yourfoods.api.ApiService
import com.gabomendez.yourfoods.api.ServiceBuilder
import com.gabomendez.yourfoods.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantPresenter: RestaurantContract.Presenter {

    private lateinit var view: RestaurantContract.View
    private val retService: ApiService =  ServiceBuilder.buildService(ApiService::class.java, "Restaurant")

    override fun getRestaurantData(country: Country) {
        this.retService.let {
            val call = retService.getRestaurantsByCity(country.colletion_id, country.city_id)
            call.enqueue(object : Callback<RestaurantResponse> {
                override fun onResponse(call: Call<RestaurantResponse>, response: Response<RestaurantResponse>) {
                    if (response.isSuccessful){
                        val result = response.body()!!.restaurants
                        val restaurants = result.filter { it.restaurant.featured_image.isNotEmpty() }
                        if (restaurants.count() > 1)
                            view.onDomainSuccess(restaurants.toMutableList())
                    }
                }

                override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                    view.onDomainError(t.message.toString())
                }
            })
        }
    }

    override fun attach(view: RestaurantContract.View) {
        this.view = view
    }
}