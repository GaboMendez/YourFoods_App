package com.gabomendez.yourfoods.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gabomendez.yourfoods.R
import com.gabomendez.yourfoods.model.Food
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_food.view.*

class FoodAdapter : RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private val items = mutableListOf<Food>()
    private val itemClickListener: ((Food, Int) -> Unit)?

    constructor() : super(){
        itemClickListener = null
    }
    constructor(itemClickListener: ((Food, Int) -> Unit)) : super(){
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.food = item
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setFoods(foods: MutableList<Food>){
        items.clear()
        items.addAll(foods)
        notifyDataSetChanged()
    }

    inner class ViewHolder(item : View) : RecyclerView.ViewHolder(item){
        var food: Food? = null

        set(value){
            value?.let {
                itemView.lblName.text = value.strMeal
                itemView.lblCategory.text = value.strCategory

                value.strCategory?.let {
                    val overlayColor = Food.getOverlayColor(it)
                    itemView.imgOverlay.background = ContextCompat.getDrawable(itemView.context, overlayColor)
                }


                Picasso.get()
                    .load(value.strMealThumb)
                    .placeholder(R.drawable.food_backgrond)
                    .into(itemView.imgBackground)
            }

            field = value
        }

        init {
            itemView.setOnClickListener{
                food?.let {
                    itemClickListener?.invoke(food as Food, adapterPosition)
                }
            }
        }
    }
}