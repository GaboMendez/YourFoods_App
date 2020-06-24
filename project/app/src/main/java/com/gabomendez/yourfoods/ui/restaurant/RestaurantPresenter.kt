package com.gabomendez.yourfoods.ui.restaurant

import com.gabomendez.yourfoods.api.*
import io.reactivex.disposables.CompositeDisposable

class RestaurantPresenter: RestaurantContract.Presenter {

    private lateinit var view: RestaurantContract.View
    private val retService: ApiService =  ServiceBuilder.buildService(ApiService::class.java, "Restaurant")
    private val subscriptions = CompositeDisposable()

    override fun getRestaurantData(countryCode: String) {
        TODO("Not yet implemented")
    }

    override fun subscribe() {
        TODO("Not yet implemented")
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: RestaurantContract.View) {
        this.view = view
    }
}