package com.gabomendez.yourfoods.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gabomendez.yourfoods.R
import com.gabomendez.yourfoods.model.Food
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(), DetailContract.View {
    private lateinit var presenter: DetailPresenter
    private var foodID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            foodID = it.getString("food_id")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgress()
        presenter = DetailPresenter()
        presenter.attach(this)

        if (!foodID.isNullOrEmpty())
            presenter.getFoodData(foodID!!)

    }

    override fun showProgress() {
        loadingBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        loadingBar.visibility = View.INVISIBLE
    }

    override fun onDomainSuccess(food: Food) {
        food?.let {
            with(food){
                lblFood.text = strMeal
                lblCategory.text = strCategory
                lblArea.text = strArea

                if (!strTags.isNullOrEmpty())
                    lblTags.text = strTags
                else
                    lblTags.visibility = View.INVISIBLE

                Picasso.get()
                    .load(strMealThumb)
                    .placeholder(R.drawable.detail_background)
                    .into(imgBackground)

                hideProgress()
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

    override fun retryRequest() {
        TODO("Not yet implemented")
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString("food_id", param1)
                }
            }
    }
}
