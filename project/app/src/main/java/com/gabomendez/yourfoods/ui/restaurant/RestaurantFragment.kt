package com.gabomendez.yourfoods.ui.restaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabomendez.yourfoods.R

class RestaurantFragment : Fragment() {
    private var countryCode: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            countryCode = it.getString("country_code")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_restaurant, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(countryCode: String) =
            RestaurantFragment().apply {
                arguments = Bundle().apply {
                    putString("country_code", countryCode)
                }
            }
    }
}
