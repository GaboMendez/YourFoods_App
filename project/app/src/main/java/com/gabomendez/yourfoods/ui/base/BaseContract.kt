package com.gabomendez.yourfoods.ui.base

interface BaseContract {

    interface BaseView{
    }

    interface BasePresenter <in T : BaseView> {
        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
    }

}

