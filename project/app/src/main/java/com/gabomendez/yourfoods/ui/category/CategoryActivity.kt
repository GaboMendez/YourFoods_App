package com.gabomendez.yourfoods.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.gabomendez.yourfoods.R

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)

        if (savedInstanceState == null){
            val fragmentFood = CategoryFragment()

            this.supportFragmentManager
                .beginTransaction()
                .add(R.id.categoryContainer, fragmentFood)
                .commit()
        }
    }
}
