package com.gabomendez.yourfoods.ui.category

import com.gabomendez.yourfoods.api.*
import io.reactivex.disposables.CompositeDisposable

class CategoryPresenter : CategoryContract.Presenter {
    private lateinit var view: CategoryContract.View
    private val retService: ApiService =  ServiceBuilder.buildService(ApiService::class.java)
    private val subscriptions = CompositeDisposable()

    override fun getData() {


    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: CategoryContract.View) {
        this.view = view
    }
}