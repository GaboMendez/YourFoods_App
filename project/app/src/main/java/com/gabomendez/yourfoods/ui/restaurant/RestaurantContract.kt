package com.gabomendez.yourfoods.ui.restaurant

import com.gabomendez.yourfoods.model.*
import com.gabomendez.yourfoods.ui.base.BaseContract

class RestaurantContract {

    interface View: BaseContract.BaseView {
        fun onDomainSuccess(restaurants: MutableList<Restaurants>)
        fun onDomainError(msg: String)
    }

    interface Presenter: BaseContract.BasePresenter<View>  {
        fun getRestaurantData(country: Country)
    }
}