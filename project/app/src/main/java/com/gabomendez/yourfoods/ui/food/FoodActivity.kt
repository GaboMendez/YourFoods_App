package com.gabomendez.yourfoods.ui.food

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabomendez.yourfoods.R
import com.gabomendez.yourfoods.adapter.FoodAdapter
import com.gabomendez.yourfoods.api.ApiService
import com.gabomendez.yourfoods.model.Food
import kotlinx.android.synthetic.main.activity_food.*

@Suppress("UNREACHABLE_CODE")
class FoodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)

        if (savedInstanceState == null){
            val fragmentFood = FoodFragment()

            this.supportFragmentManager
                .beginTransaction()
                .add(R.id.foodContainer, fragmentFood)
                .commit()
        }
    }
}