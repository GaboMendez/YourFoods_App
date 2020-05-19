package com.gabomendez.yourfoods.ui.food

import android.content.Context
import android.widget.Toast
import com.gabomendez.yourfoods.api.ApiService
import com.gabomendez.yourfoods.api.ServiceBuilder
import com.gabomendez.yourfoods.model.Food
import com.gabomendez.yourfoods.model.FoodResponse
import io.reactivex.disposables.CompositeDisposable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodPresenter: FoodContract.Presenter {

    private val retService: ApiService =  ServiceBuilder.buildService(ApiService::class.java)
    private val subscriptions = CompositeDisposable()
    private val listFoods = mutableListOf<Food>()
    private lateinit var view: FoodContract.View

    override fun getData() {
        for (number in 0..9){
            getFood()
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
                    view.onDomainError(t.message.toString())
                }
            })
        }
    }

    override fun onLoadFoodTapped() {
        TODO("Not yet implemented")
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: FoodContract.View) {
        this.view=view
    }



}