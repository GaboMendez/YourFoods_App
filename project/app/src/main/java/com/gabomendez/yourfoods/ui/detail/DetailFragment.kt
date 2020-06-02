package com.gabomendez.yourfoods.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabomendez.yourfoods.R

class DetailFragment : Fragment() {
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
