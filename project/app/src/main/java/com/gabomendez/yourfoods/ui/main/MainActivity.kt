package com.gabomendez.yourfoods.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentManager
import com.gabomendez.yourfoods.R
import com.gabomendez.yourfoods.adapter.PageAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.let {
            it.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            it.setCustomView(R.layout.action_bar_layout)
            it.elevation = 0F
        }

        viewPager.adapter = PageAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

        //supportActionBar?.hide()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if ( supportFragmentManager.findFragmentByTag("category-food") != null ){
            supportFragmentManager.popBackStack("category", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

    }
}
