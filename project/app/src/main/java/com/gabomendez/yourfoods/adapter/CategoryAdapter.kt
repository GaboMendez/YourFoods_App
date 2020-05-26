package com.gabomendez.yourfoods.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gabomendez.yourfoods.R
import com.gabomendez.yourfoods.model.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private val items = mutableListOf<Category>()
    private val itemClickListener: ((Category, Int) -> Unit)?

    constructor() : super(){
        itemClickListener = null
    }

    constructor(itemClickListener: ((Category, Int) -> Unit)) : super(){
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.category = item
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(item : View) : RecyclerView.ViewHolder(item){
        var category: Category? = null

            set(value){
                value?.let {
                    itemView.lblCategory.text = value.strCategory
                    itemView.lblDescription.text = value.strCategoryDescription

                    value.strCategory?.let {
                        val overlayColor = Category.getBaseColor(it)
                        itemView.imgOverlay.background = ContextCompat.getDrawable(itemView.context, overlayColor)
                    }

                    Picasso.get()
                        .load(value.strCategoryThumb)
                        .placeholder(R.drawable.food_backgrond)
                        .into(itemView.imgBackground)
                }

                field = value
            }

        init {
            itemView.setOnClickListener{
                category?.let {
                    itemClickListener?.invoke(category as Category, adapterPosition)
                }
            }
        }
    }

}