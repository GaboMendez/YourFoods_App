package com.gabomendez.yourfoods.ui.detail

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.*
import com.gabomendez.yourfoods.R
import com.gabomendez.yourfoods.adapter.IngredientAdapter
import com.gabomendez.yourfoods.model.Food
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(), DetailContract.View {
    private lateinit var presenter: DetailPresenter
    private var foodID: String? = null
    private var actualFood: Food? = null

    private val ingredientsList: RecyclerView by lazy {
        val list: RecyclerView = view!!.findViewById(R.id.recyclerIngredients)
        list.layoutManager = LinearLayoutManager(context)
        list
    }

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

        btnInstructions.setOnClickListener { showInstructions() }
    }

    override fun showProgress() {
        loadingBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        loadingBar.visibility = View.INVISIBLE
    }

    override fun setMeasuresAndIngredients(food: Food){
        val ret = mutableListOf<String>()
        with(food){
            if (!strMeasure1.isNullOrBlank() && !strIngredient1.isNullOrBlank())
                ret.add("$strMeasure1-of $strIngredient1")
            if (!strMeasure2.isNullOrBlank() && !strIngredient2.isNullOrBlank())
                ret.add("$strMeasure2-of $strIngredient2")
            if (!strMeasure3.isNullOrBlank() && !strIngredient3.isNullOrBlank())
                ret.add("$strMeasure3-of $strIngredient3")
            if (!strMeasure4.isNullOrBlank() && !strIngredient4.isNullOrBlank())
                ret.add("$strMeasure4-of $strIngredient4")
            if (!strMeasure5.isNullOrBlank() && !strIngredient5.isNullOrBlank())
                ret.add("$strMeasure5-of $strIngredient5")
            if (!strMeasure6.isNullOrBlank() && !strIngredient6.isNullOrBlank())
                ret.add("$strMeasure6-of $strIngredient6")
            if (!strMeasure7.isNullOrBlank() && !strIngredient7.isNullOrBlank())
                ret.add("$strMeasure7-of $strIngredient7")
            if (!strMeasure8.isNullOrBlank() && !strIngredient8.isNullOrBlank())
                ret.add("$strMeasure8-of $strIngredient8")
            if (!strMeasure9.isNullOrBlank() && !strIngredient9.isNullOrBlank())
                ret.add("$strMeasure9-of $strIngredient9")
            if (!strMeasure10.isNullOrBlank() && !strIngredient10.isNullOrBlank())
                ret.add("$strMeasure10-of $strIngredient10")
            if (!strMeasure11.isNullOrBlank() && !strIngredient11.isNullOrBlank())
                ret.add("$strMeasure11-of $strIngredient11")
            if (!strMeasure12.isNullOrBlank() && !strIngredient12.isNullOrBlank())
                ret.add("$strMeasure12-of $strIngredient12")
            if (!strMeasure13.isNullOrBlank() && !strIngredient13.isNullOrBlank())
                ret.add("$strMeasure13-of $strIngredient13")
            if (!strMeasure14.isNullOrBlank() && !strIngredient14.isNullOrBlank())
                ret.add("$strMeasure14-of $strIngredient14")
            if (!strMeasure15.isNullOrBlank() && !strIngredient15.isNullOrBlank())
                ret.add("$strMeasure15-of $strIngredient15")
            if (!strMeasure16.isNullOrBlank() && !strIngredient16.isNullOrBlank())
                ret.add("$strMeasure16-of $strIngredient16")
            if (!strMeasure17.isNullOrBlank() && !strIngredient17.isNullOrBlank())
                ret.add("$strMeasure17-of $strIngredient17")
            if (!strMeasure18.isNullOrBlank() && !strIngredient18.isNullOrBlank())
                ret.add("$strMeasure18-of $strIngredient18")
            if (!strMeasure19.isNullOrBlank() && !strIngredient19.isNullOrBlank())
                ret.add("$strMeasure19-of $strIngredient19")
            if (!strMeasure20.isNullOrBlank() && !strIngredient20.isNullOrBlank())
                ret.add("$strMeasure20-of $strIngredient20")
        }

        val newAdapter = IngredientAdapter()
        newAdapter.setIngredients(ret)
        ingredientsList.apply {
            adapter = newAdapter
            (recyclerIngredients.adapter as IngredientAdapter).notifyDataSetChanged()
        }
    }

    override fun onDomainSuccess(food: Food) {
        food?.let {
            with(food){
                actualFood = food
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

                setMeasuresAndIngredients(it)
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

    override fun showInstructions(){
        actualFood?.let {
            val dialog = AlertDialog.Builder(context)
                .setTitle("Instructions")
                .setMessage(it.strInstructions)
                .setPositiveButton("Ok", null)

            dialog.show()
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
