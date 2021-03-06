package com.gabomendez.yourfoods.model

import com.gabomendez.yourfoods.R
import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("meals")
    val results: MutableList<Food>
)

data class Food (
    val idMeal : Int = 0,
    val strMeal : String? = null,
    val strDrinkAlternate : String? = null,
    var strCategory : String? = null,
    val strArea : String? = null,
    val strInstructions : String? = null,
    val strMealThumb : String? = null,
    val strTags : String? = null,
    val strYoutube : String? = null,
    val strIngredient1 : String? = null,
    val strIngredient2 : String? = null,
    val strIngredient3 : String? = null,
    val strIngredient4 : String? = null,
    val strIngredient5 : String? = null,
    val strIngredient6 : String? = null,
    val strIngredient7 : String? = null,
    val strIngredient8 : String? = null,
    val strIngredient9 : String? = null,
    val strIngredient10 : String? = null,
    val strIngredient11 : String? = null,
    val strIngredient12 : String? = null,
    val strIngredient13 : String? = null,
    val strIngredient14 : String? = null,
    val strIngredient15 : String? = null,
    val strIngredient16 : String? = null,
    val strIngredient17 : String? = null,
    val strIngredient18 : String? = null,
    val strIngredient19 : String? = null,
    val strIngredient20 : String? = null,
    val strMeasure1 : String? = null,
    val strMeasure2 : String? = null,
    val strMeasure3 : String? = null,
    val strMeasure4 : String? = null,
    val strMeasure5 : String? = null,
    val strMeasure6 : String? = null,
    val strMeasure7 : String? = null,
    val strMeasure8 : String? = null,
    val strMeasure9 : String? = null,
    val strMeasure10 : String? = null,
    val strMeasure11 : String? = null,
    val strMeasure12 : String? = null,
    val strMeasure13 : String? = null,
    val strMeasure14 : String? = null,
    val strMeasure15 : String? = null,
    val strMeasure16 : String? = null,
    val strMeasure17 : String? = null,
    val strMeasure18 : String? = null,
    val strMeasure19 : String? = null,
    val strMeasure20 : String? = null,
    val strSource : String? = null
) {
    companion object {
        private val DEFAULT_PALETTE = arrayOf(R.color.beefOverlay, R.color.beefBase)
        private val colors = mapOf(
            Pair("Beef", arrayOf(R.color.beefOverlay, R.color.beefBase)),
            Pair("Chicken", arrayOf(R.color.chickenOverlay, R.color.chickenBase)),
            Pair("Dessert", arrayOf(R.color.dessertOverlay, R.color.dessertBase)),
            Pair("Lamb", arrayOf(R.color.lambOverlay, R.color.lambBase)),
            Pair("Miscellaneous", arrayOf(R.color.miscellaneousOverlay, R.color.miscellaneousBase)),
            Pair("Pork", arrayOf(R.color.porkOverlay, R.color.porkBase)),
            Pair("Seafood", arrayOf(R.color.seafoodOverlay, R.color.seafoodBase)),
            Pair("Side", arrayOf(R.color.sideOverlay, R.color.sideBase)),
            Pair("Starter", arrayOf(R.color.starterOverlay, R.color.starterBase)),
            Pair("Vegan", arrayOf(R.color.veganOverlay, R.color.veganBase)),
            Pair("Vegetarian", arrayOf(R.color.vegetarianOverlay, R.color.vegetarianBase)),
            Pair("Breakfast", arrayOf(R.color.breakfastOverlay, R.color.breakfastBase)),
            Pair("Goat", arrayOf(R.color.goatOverlay, R.color.goatBase))
        )

        fun getOverlayColor(category: String): Int {
            val palette = getPalette(category)
            return palette[0]
        }

        fun getBaseColor(category: String): Int {
            val palette = getPalette(category)
            return palette[1]
        }

        private fun getPalette(category: String) : Array<Int> {
            val palette = colors[category]
            return palette ?: DEFAULT_PALETTE
        }
    }
}