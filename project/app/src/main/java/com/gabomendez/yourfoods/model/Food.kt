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
    val strCategory : String? = null,
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
    val strSource : String? = null
) {
    companion object {
        private val DEFAULT_PALETTE = arrayOf(R.color.beefOverlay)
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

        fun getOverlayColor(houseId: String): Int {
            val palette = getPalette(houseId)
            return palette[0]
        }

        fun getBaseColor(houseId: String): Int {
            val palette = getPalette(houseId)
            return palette[1]
        }

        fun getIcon(houseId: String): Int {
            val palette = getPalette(houseId)
            return palette[2]
        }

        private fun getPalette(houseId: String) : Array<Int> {
            val palette = colors[houseId]
            return palette ?: DEFAULT_PALETTE
        }
    }
}