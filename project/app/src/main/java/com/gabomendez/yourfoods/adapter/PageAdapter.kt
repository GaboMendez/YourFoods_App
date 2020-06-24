package com.gabomendez.yourfoods.adapter

import androidx.fragment.app.*
import com.gabomendez.yourfoods.ui.restaurant.MapFragment
import com.gabomendez.yourfoods.ui.category.CategoryFragment
import com.gabomendez.yourfoods.ui.food.FoodFragment


class PageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) { //FragmentPagerAdapter

    override fun getPageTitle(position: Int): CharSequence? {
        when (position){
            0 -> {
                return "Food"
            }
            1 -> {
                return "Category"
            }
            2 -> {
                return "Restaurant"
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
            2 -> {
                MapFragment()
            }
            else -> {
                CategoryFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }
}