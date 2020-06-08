package com.gabomendez.yourfoods.ui.food

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabomendez.yourfoods.R
import com.gabomendez.yourfoods.adapter.FoodAdapter
import com.gabomendez.yourfoods.model.Food
import com.gabomendez.yourfoods.ui.category.CategoryFragment
import com.gabomendez.yourfoods.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.fragment_food.*
import kotlinx.android.synthetic.main.fragment_food.view.*
import kotlinx.android.synthetic.main.item_error.view.*
import java.lang.IllegalArgumentException

class FoodFragment : Fragment(), FoodContract.View {

    private lateinit var presenter: FoodPresenter
    private var categoryName: String? = null

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryName = it.getString("category_name")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgress()
        presenter = FoodPresenter()
        presenter.attach(this)

        if (categoryName.isNullOrBlank())
            presenter.getData()
        else{
            presenter.getCategoryData(categoryName!!)
            layoutRefresh.isEnabled = false
        }

        layoutRefresh.setOnRefreshListener { refreshData() }
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

    override fun refreshData(){
        layoutRefresh.isRefreshing = true
        presenter.listFoods.clear()
        presenter.getData()
    }

    override fun onFoodTapped(food: Food) {
        val trans = fragmentManager!!.beginTransaction()
        val detailFragment = DetailFragment.newInstance( food.idMeal.toString() )
        trans.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)

        if(categoryName.isNullOrBlank()){
            trans.replace(R.id.foodContainer, detailFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
        }else{
            trans.replace(R.id.categoryContainer, detailFragment, "category-food")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
        }

    }

    override fun onDomainSuccess(foods: MutableList<Food>) {
        context?.let {
            view?.let {
                hideProgress()
                layoutRefresh.visibility = View.VISIBLE
                foodAdapter.setFoods(foods)
                foodList.apply {
                    layoutManager = GridLayoutManager(this.context, 2)
                    adapter = foodAdapter
                    (recyclerFood.adapter as FoodAdapter).notifyDataSetChanged()
                }

                if (layoutRefresh.isRefreshing)
                    layoutRefresh.isRefreshing = false
            }
        }
    }

    override fun onDomainError(msg: String) {
        context?.let {
            view?.let {
                hideProgress()
                layoutRefresh.visibility = View.INVISIBLE
                layoutError.visibility = View.VISIBLE
                Toast.makeText(this.context,msg, Toast.LENGTH_LONG).show()

                if (layoutRefresh.isRefreshing)
                    layoutRefresh.isRefreshing = false
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            FoodFragment().apply {
                arguments = Bundle().apply {
                    putString("category_name", param1)
                }
            }
    }

}