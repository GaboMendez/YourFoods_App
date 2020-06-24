package com.gabomendez.yourfoods.ui.detail

import com.gabomendez.yourfoods.api.*
import com.gabomendez.yourfoods.model.FoodResponse
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter: DetailContract.Presenter  {

    private lateinit var view: DetailContract.View
    private val retService: ApiService =  ServiceBuilder.buildService(ApiService::class.java, "Meal")
    private val subscriptions = CompositeDisposable()

    override fun getFoodData(foodID: String) {
        this.retService.let {
            val call = retService.getFoodByID(foodID)
            call.enqueue(object : Callback<FoodResponse> {
                override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                    if (response.isSuccessful){
                        val food = response.body()!!.results.firstOrNull()

                        if (food != null)
                            view.onDomainSuccess(food)
                    }
                }

                override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                    view.onDomainError(t.message.toString())
                }
            })
        }
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: DetailContract.View) {
        this.view = view
    }
}