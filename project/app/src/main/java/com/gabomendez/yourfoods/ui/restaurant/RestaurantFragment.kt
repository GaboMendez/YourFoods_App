package com.gabomendez.yourfoods.ui.restaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabomendez.yourfoods.R
import com.gabomendez.yourfoods.model.Restaurant
import kotlinx.android.synthetic.main.fragment_restaurant.*

class RestaurantFragment : Fragment(), RestaurantContract.View {
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

    override fun showProgress() {
        loadingBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        loadingBar.visibility = View.INVISIBLE
    }

    override fun onDomainSuccess(restaurants: MutableList<Restaurant>) {
        TODO("Not yet implemented")
    }

    override fun onDomainError(msg: String) {
        TODO("Not yet implemented")
    }

    override fun retryRequest() {
        TODO("Not yet implemented")
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
