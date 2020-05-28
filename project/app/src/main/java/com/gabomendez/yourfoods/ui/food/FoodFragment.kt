package com.gabomendez.yourfoods.ui.food

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabomendez.yourfoods.R
import com.gabomendez.yourfoods.adapter.FoodAdapter
import com.gabomendez.yourfoods.model.Food
import com.gabomendez.yourfoods.ui.category.CategoryFragment
import kotlinx.android.synthetic.main.fragment_food.*
import kotlinx.android.synthetic.main.fragment_food.view.*
import kotlinx.android.synthetic.main.item_error.view.*
import java.lang.IllegalArgumentException

class FoodFragment : Fragment(), FoodContract.View {

    private lateinit var presenter: FoodPresenter
    private val foodList: RecyclerView by lazy {
        val list: RecyclerView = view!!.findViewById(R.id.recyclerFood)
        list.layoutManager = LinearLayoutManager(context)
        list
    }

    private val foodAdapter: FoodAdapter by lazy {
        val adapter = FoodAdapter() { item, position ->
            onFoodTapped(item)
        }
        adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgress()
        presenter = FoodPresenter()
        presenter.attach(this)
        presenter.getData()

        layoutError.btnTryAgain.setOnClickListener { retryRequest() }
    }

    override fun showProgress() {
        loadingBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        loadingBar.visibility = View.GONE
    }

    override fun retryRequest() {
        layoutError.visibility = View.INVISIBLE
        showProgress()
        presenter.isRepeatingError = false
        presenter.getData()
    }

    override fun onFoodTapped(food: Food) {
        println()
    }

    override fun onDomainSuccess(foods: MutableList<Food>) {
        context?.let {
            view?.let {
                hideProgress()
                foodAdapter.setFoods(foods)
                foodList.apply {
                    visibility = View.VISIBLE
                    layoutManager = GridLayoutManager(this.context, 2)
                    adapter = foodAdapter
                    (recyclerFood.adapter as FoodAdapter).notifyDataSetChanged()
                }
            }
        }
    }

    override fun onDomainError(msg: String) {
        context?.let {
            view?.let {
                hideProgress()
                layoutError.visibility = View.VISIBLE
                Toast.makeText(this.context,msg, Toast.LENGTH_LONG).show()
            }
        }
    }

}