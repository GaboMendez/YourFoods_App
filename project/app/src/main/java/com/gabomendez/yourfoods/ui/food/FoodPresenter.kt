package com.gabomendez.yourfoods.ui.food

import com.gabomendez.yourfoods.api.ApiService
import com.gabomendez.yourfoods.api.ServiceBuilder
import com.gabomendez.yourfoods.model.Food
import com.gabomendez.yourfoods.model.FoodResponse
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodPresenter: FoodContract.Presenter {

    private lateinit var view: FoodContract.View
    private val retService: ApiService =  ServiceBuilder.buildService(ApiService::class.java)
    private val subscriptions = CompositeDisposable()
    var listFoods = mutableListOf<Food>()
    var isRepeatingError = false

    override fun getData() {
        for (number in 0..15){
            getFood()
        }
    }

    override fun getCategoryData(category: String) {
        this.retService.let {
            val call = retService.getFoodsByCategory(category)
            call.enqueue(object : Callback<FoodResponse> {
                override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                    if (response.isSuccessful){
                        val categories = response.body()!!.results

                        for (value in categories)
                            value.strCategory = category

                        if (categories.count() > 0)
                            view.onDomainSuccess(categories)
                    }
                }

                override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                    view.onDomainError(t.message.toString())
                }
            })
        }
    }

    override fun getFood() {
        this.retService.let {
            val call = retService.getRandomFood()
            call.enqueue(object : Callback<FoodResponse>{
                override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                    if (response.isSuccessful){
                        val food = response.body()!!.results.firstOrNull()
                        if (food != null)
                            listFoods.add(food)

                        if (listFoods.size >= 10)
                            view.onDomainSuccess(listFoods)
                    }
                }

                override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                    if (!isRepeatingError)
                        view.onDomainError(t.message.toString())

                    isRepeatingError = true
                }
            })
        }
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: FoodContract.View) {
        this.view = view
    }



}