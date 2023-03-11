package com.example.foods.presentation.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foods.core.extensions.binding
import com.example.foods.databinding.FoodRecipeItemViewBinding
import com.example.foods.domain.dto.FoodRecipeItemDTO
import com.example.foods.presentation.holders.FoodRecipeViewHolder

class FoodRecipesAdapter(
    private val listener: (Int) -> Unit
) : RecyclerView.Adapter<FoodRecipeViewHolder>() {

    private var foodRecipesList = mutableListOf<FoodRecipeItemDTO>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<FoodRecipeItemDTO>) {
        foodRecipesList.clear()
        foodRecipesList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodRecipeViewHolder {
        return FoodRecipeViewHolder(parent.binding(FoodRecipeItemViewBinding::inflate), listener)
    }

    override fun getItemCount(): Int = foodRecipesList.size

    override fun onBindViewHolder(holder: FoodRecipeViewHolder, position: Int) {
        holder.bind(foodRecipesList[position])
    }
}
