package com.gabomendez.yourfoods.ui.category

import com.gabomendez.yourfoods.ui.base.BaseContract

class CategoryContract {

    interface View: BaseContract.BaseView{
        fun showProgress()
        fun hideProgress()
        fun retryRequest()
    }

    interface Presenter: BaseContract.BasePresenter<View>  {
        fun getData()
    }
}