package com.gabomendez.yourfoods.model

import com.gabomendez.yourfoods.R

data class CategoryResponse(
    val categories: MutableList<Category>
)

data class Category (
    val idCategory : Int = 0,
    val strCategory : String? = null,
    val strCategoryThumb : String? = null,
    val strCategoryDescription : String? = null
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