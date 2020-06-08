package com.gabomendez.yourfoods.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabomendez.yourfoods.R

class IngredientAdapter : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    private val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.item = item
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setIngredients(ingredients: MutableList<String>){
        items.clear()
        items.addAll(ingredients)
        notifyDataSetChanged()
    }

    inner class ViewHolder(item : View) : RecyclerView.ViewHolder(item){
        var item: String? = null

            set(value){
                value?.let {
                    //itemView.lblCategory.text = value.strCategory
                    //itemView.lblDescription.text = substringDescription(value.strCategoryDescription)
                }

                field = value
            }

    }

}