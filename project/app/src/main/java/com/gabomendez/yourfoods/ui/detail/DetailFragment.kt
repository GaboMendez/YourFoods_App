package com.gabomendez.yourfoods.ui.detail

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabomendez.yourfoods.R
import com.gabomendez.yourfoods.adapter.IngredientAdapter
import com.gabomendez.yourfoods.model.Food
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.layoutError
import kotlinx.android.synthetic.main.fragment_detail.loadingBar
import kotlinx.android.synthetic.main.header_detail.*
import kotlinx.android.synthetic.main.ingredient_detail.*
import kotlinx.android.synthetic.main.item_error.*

class DetailFragment : Fragment(), DetailContract.View {
    private lateinit var presenter: DetailPresenter
    private lateinit var actualFood: Food
    private var foodID: String? = null

    private val ingredientsList: RecyclerView by lazy {
        val list: RecyclerView = view!!.findViewById(R.id.recyclerIngredients)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = IngredientAdapter()
        list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            foodID = it.getString("food_id")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgress()
        presenter = DetailPresenter()
        presenter.attach(this)

        if (!foodID.isNullOrEmpty())
            presenter.getFoodData(foodID!!)

        btnTryAgain.setOnClickListener { retryRequest() }
        btnInstructions.setOnClickListener { showInstructions() }
    }

    override fun showProgress() {
        layoutDetail.visibility = View.INVISIBLE
        loadingBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        loadingBar.visibility = View.INVISIBLE
    }

    override fun setMeasuresAndIngredients(food: Food) {
        val ret = mutableListOf<String>()
        with(food) {
            if (!strMeasure1.isNullOrBlank() && !strIngredient1.isNullOrBlank())
                ret.add("$strMeasure1-$strIngredient1")
            if (!strMeasure2.isNullOrBlank() && !strIngredient2.isNullOrBlank())
                ret.add("$strMeasure2-$strIngredient2")
            if (!strMeasure3.isNullOrBlank() && !strIngredient3.isNullOrBlank())
                ret.add("$strMeasure3-$strIngredient3")
            if (!strMeasure4.isNullOrBlank() && !strIngredient4.isNullOrBlank())
                ret.add("$strMeasure4-$strIngredient4")
            if (!strMeasure5.isNullOrBlank() && !strIngredient5.isNullOrBlank())
                ret.add("$strMeasure5-$strIngredient5")
            if (!strMeasure6.isNullOrBlank() && !strIngredient6.isNullOrBlank())
                ret.add("$strMeasure6-$strIngredient6")
            if (!strMeasure7.isNullOrBlank() && !strIngredient7.isNullOrBlank())
                ret.add("$strMeasure7-$strIngredient7")
            if (!strMeasure8.isNullOrBlank() && !strIngredient8.isNullOrBlank())
                ret.add("$strMeasure8-$strIngredient8")
            if (!strMeasure9.isNullOrBlank() && !strIngredient9.isNullOrBlank())
                ret.add("$strMeasure9-$strIngredient9")
            if (!strMeasure10.isNullOrBlank() && !strIngredient10.isNullOrBlank())
                ret.add("$strMeasure10-$strIngredient10")
            if (!strMeasure11.isNullOrBlank() && !strIngredient11.isNullOrBlank())
                ret.add("$strMeasure11-$strIngredient11")
            if (!strMeasure12.isNullOrBlank() && !strIngredient12.isNullOrBlank())
                ret.add("$strMeasure12-$strIngredient12")
            if (!strMeasure13.isNullOrBlank() && !strIngredient13.isNullOrBlank())
                ret.add("$strMeasure13-$strIngredient13")
            if (!strMeasure14.isNullOrBlank() && !strIngredient14.isNullOrBlank())
                ret.add("$strMeasure14-$strIngredient14")
            if (!strMeasure15.isNullOrBlank() && !strIngredient15.isNullOrBlank())
                ret.add("$strMeasure15-$strIngredient15")
            if (!strMeasure16.isNullOrBlank() && !strIngredient16.isNullOrBlank())
                ret.add("$strMeasure16-$strIngredient16")
            if (!strMeasure17.isNullOrBlank() && !strIngredient17.isNullOrBlank())
                ret.add("$strMeasure17-$strIngredient17")
            if (!strMeasure18.isNullOrBlank() && !strIngredient18.isNullOrBlank())
                ret.add("$strMeasure18-$strIngredient18")
            if (!strMeasure19.isNullOrBlank() && !strIngredient19.isNullOrBlank())
                ret.add("$strMeasure19-$strIngredient19")
        }

        val newAdapter = IngredientAdapter()
        newAdapter.setIngredients(ret)
        ingredientsList.apply {
            adapter = newAdapter
            (recyclerIngredients.adapter as IngredientAdapter).notifyDataSetChanged()
        }
    }

    override fun onDomainSuccess(food: Food) {
        with(food) {
            detailContainer.setBackgroundColor(
                ContextCompat.getColor(
                    context!!,
                    R.color.colorWhite
                )
            );
            actualFood = this
            layoutDetail.visibility = View.VISIBLE
            lblFood.text = strMeal
            lblCategory.text = strCategory
            lblArea.text = strArea

            if (!strTags.isNullOrEmpty())
                lblTags.text = strTags
            else
                lblTags.visibility = View.INVISIBLE

            if (!strYoutube.isNullOrBlank()) {
                btnTutorial.setOnClickListener { showTutorial() }
                btnTutorial.visibility = View.VISIBLE
            } else
                btnTutorial.visibility = View.GONE

            Picasso.get()
                .load(strMealThumb)
                .placeholder(R.drawable.detail_background)
                .into(imgBackground)

            setMeasuresAndIngredients(food)
            hideProgress()
        }
    }

    override fun onDomainError(msg: String) {
        context?.let {
            view?.let {
                detailContainer.setBackgroundColor(
                    ContextCompat.getColor(
                        context!!,
                        R.color.colorBackground
                    )
                );
                layoutDetail.visibility = View.INVISIBLE
                hideProgress()
                layoutError.visibility = View.VISIBLE
                Toast.makeText(this.context, msg, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun retryRequest() {
        layoutError.visibility = View.INVISIBLE
        showProgress()
        presenter.getFoodData(foodID!!)
    }

    override fun showInstructions() {
        actualFood.let {
            val dialog = AlertDialog.Builder(context)
                .setTitle("Instructions")
                .setMessage(it.strInstructions)
                .setPositiveButton("Ok", null)

            dialog.show()
        }
    }

    override fun showTutorial() {
        actualFood.let {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.strYoutube))
            startActivity(intent)
        }
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
