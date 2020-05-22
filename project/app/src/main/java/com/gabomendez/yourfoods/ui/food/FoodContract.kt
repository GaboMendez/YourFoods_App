package com.gabomendez.yourfoods.ui.food

import com.gabomendez.yourfoods.model.Food
import com.gabomendez.yourfoods.ui.base.BaseContract


class FoodContract {

    interface View: BaseContract.BaseView{
        fun showProgress()
        fun hideProgress()
        fun onDomainSuccess(foods: MutableList<Food>)
        fun onDomainError(msg: String)
        fun retryRequest()
    }

    interface Presenter: BaseContract.BasePresenter<View>  {
        fun getData()
        fun getFood()
        fun onLoadFoodTapped()
    }

}