package com.gabomendez.yourfoods.ui

class BaseContract {

    interface BaseView{
        fun isActive(): Boolean
    }

    interface BasePresenter <in T : BaseView> {
        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
    }

}

