package com.gabomendez.yourfoods.ui.food

import com.gabomendez.yourfoods.model.Food
import com.gabomendez.yourfoods.ui.base.BaseContract

class FoodContract {

    interface View: BaseContract.BaseView {
        fun onFoodTapped(food: Food)
        fun onDomainSuccess(foods: MutableList<Food>)
        fun onDomainError(msg: String)
        fun refreshData()
    }

    interface Presenter: BaseContract.BasePresenter<View>  {
        fun getData()
        fun getCategoryData(category: String)
        fun getFood()
    }

}