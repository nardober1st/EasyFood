package com.bernardooechsler.easyfood.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bernardooechsler.easyfood.databinding.MealItemBinding
import com.bernardooechsler.easyfood.pojo.Meal
import com.bumptech.glide.Glide

class MealsAdapter :
    RecyclerView.Adapter<MealsAdapter.FavoritesMealViewHolder>() {

    inner class FavoritesMealViewHolder(val binding: MealItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    lateinit var onItemClick: ((Meal) -> Unit)

    private val diffUtil = object : DiffUtil.ItemCallback<Meal>() {

        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesMealViewHolder {
        return FavoritesMealViewHolder(
            MealItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FavoritesMealViewHolder, position: Int) {
        val meal = differ.currentList[position]
        Glide.with(
            holder.itemView
        ).load(meal.strMealThumb).into(holder.binding.imgMeal)
        holder.binding.tvMealName.text = meal.strMeal

        holder.itemView.setOnClickListener {
            onItemClick.invoke(meal)
        }
    }
}