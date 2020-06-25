package com.gabomendez.yourfoods.ui.restaurant

import com.gabomendez.yourfoods.api.*

class RestaurantPresenter: RestaurantContract.Presenter {

    private lateinit var view: RestaurantContract.View
    private val retService: ApiService =  ServiceBuilder.buildService(ApiService::class.java, "Restaurant")

    override fun getRestaurantData(countryCode: String) {
        TODO("Not yet implemented")
    }

    override fun attach(view: RestaurantContract.View) {
        this.view = view
    }
}