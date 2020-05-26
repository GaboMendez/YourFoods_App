package com.gabomendez.yourfoods.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabomendez.yourfoods.R
import com.gabomendez.yourfoods.adapter.CategoryAdapter
import com.gabomendez.yourfoods.model.Category
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : Fragment(), CategoryContract.View {

    private lateinit var presenter: CategoryPresenter

    val categoryList: RecyclerView by lazy {
        val list: RecyclerView = view!!.findViewById(R.id.recyclerCategory)
        list.layoutManager = LinearLayoutManager(context)
        list
    }

    val categoryAdapter: CategoryAdapter by lazy {
        val adapter = CategoryAdapter() { item, position ->
            onCategoryTapped(item)
        }
        adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println()
    }

    override fun showProgress() {
        loadingBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        loadingBar.visibility = View.INVISIBLE
    }

    override fun retryRequest() {
        TODO("Not yet implemented")
    }

    override fun onCategoryTapped(category: Category) {
        println()
    }

}