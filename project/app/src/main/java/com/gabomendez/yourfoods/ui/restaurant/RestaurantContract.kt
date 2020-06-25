package com.gabomendez.yourfoods.ui.restaurant

import com.gabomendez.yourfoods.model.Restaurant
import com.gabomendez.yourfoods.ui.base.BaseContract

class RestaurantContract {

    interface View: BaseContract.BaseView {
        fun onDomainSuccess(restaurants: MutableList<Restaurant>)
        fun onDomainError(msg: String)
        fun refreshData()
    }

    interface Presenter: BaseContract.BasePresenter<View>  {
        fun getRestaurantData(countryCode: String)
    }
}