package com.gabomendez.yourfoods.ui.base

interface BaseContract {

    interface BaseView{
        fun showProgress()
        fun hideProgress()
        fun retryRequest()
    }

    interface BasePresenter <in T : BaseView> {
        fun attach(view: T)
    }

}

