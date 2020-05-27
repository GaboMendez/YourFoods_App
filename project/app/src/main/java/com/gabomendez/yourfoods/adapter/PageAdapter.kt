package com.gabomendez.yourfoods.adapter

import androidx.fragment.app.*
import com.gabomendez.yourfoods.ui.category.CategoryFragment
import com.gabomendez.yourfoods.ui.food.FoodFragment


class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        when (position){
            0 -> {
                return "Food"
            }
            1 -> {
                return "Category"
            }
        }
        return super.getPageTitle(position)
    }

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> {
                FoodFragment()
            }
            1 -> {
                CategoryFragment()
            }
            else -> {
                FoodFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}