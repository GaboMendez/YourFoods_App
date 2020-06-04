package com.gabomendez.yourfoods.ui.detail

import com.gabomendez.yourfoods.model.Food
import com.gabomendez.yourfoods.ui.base.BaseContract

class DetailContract {

    interface View: BaseContract.BaseView {
        fun showProgress()
        fun hideProgress()
        fun onDomainSuccess(food: Food)
        fun onDomainError(msg: String)
        fun retryRequest()
    }

    interface Presenter: BaseContract.BasePresenter<View>  {
        fun getFoodData(foodID: String)
    }

}