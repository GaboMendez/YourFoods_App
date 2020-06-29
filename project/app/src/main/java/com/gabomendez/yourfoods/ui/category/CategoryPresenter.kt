package com.gabomendez.yourfoods.ui.category

import com.gabomendez.yourfoods.api.*
import com.gabomendez.yourfoods.model.CategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryPresenter : CategoryContract.Presenter {
    private lateinit var view: CategoryContract.View
    private val retService: ApiService =  ServiceBuilder.buildService(ApiService::class.java, "Meal")

    override fun getData() {
        this.retService.let {
            val call = retService.getCategories()
            call.enqueue(object : Callback<CategoryResponse> {
                override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                    if (response.isSuccessful){
                        val categories = response.body()!!.categories

                        if (categories.count() > 10)
                            view.onDomainSuccess(categories)
                    }
                }

                override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                    view.onDomainError(t.message.toString())
                }
            })
        }
    }

    override fun attach(view: CategoryContract.View) {
        this.view = view
    }
}