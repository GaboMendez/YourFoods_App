package com.gabomendez.yourfoods.ui.category

import com.gabomendez.yourfoods.model.Category
import com.gabomendez.yourfoods.ui.base.BaseContract

class CategoryContract {

    interface View: BaseContract.BaseView{
        fun showProgress()
        fun hideProgress()
        fun onCategoryTapped(category: Category)
        fun onDomainSuccess(categories: MutableList<Category>)
        fun onDomainError(msg: String)
        fun retryRequest()
    }

    interface Presenter: BaseContract.BasePresenter<View>  {
        fun getData()
    }
}