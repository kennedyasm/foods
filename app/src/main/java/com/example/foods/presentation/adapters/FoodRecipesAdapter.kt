package com.example.foods.presentation.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foods.core.extensions.binding
import com.example.foods.databinding.FoodRecipeItemViewBinding
import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.presentation.holders.FoodRecipeViewHolder

class FoodRecipesAdapter(
    private val listener: (FoodRecipeItemUi) -> Unit
) : RecyclerView.Adapter<FoodRecipeViewHolder>() {

    private var foodRecipesList = mutableListOf<FoodRecipeItemUi>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<FoodRecipeItemUi>) {
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
