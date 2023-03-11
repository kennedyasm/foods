package com.example.foods.presentation.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.foods.core.extensions.loadImage
import com.example.foods.databinding.FoodRecipeItemViewBinding
import com.example.foods.domain.dto.FoodRecipeItemDTO

class FoodRecipeViewHolder(
    private val foodRecipeItemViewBinding: FoodRecipeItemViewBinding,
    private val listener: (Int) -> Unit
): RecyclerView.ViewHolder(foodRecipeItemViewBinding.root) {

    fun bind(item: FoodRecipeItemDTO) = foodRecipeItemViewBinding.run {
        foodRecipeTitle.text = item.name
        foodRecipeImage.loadImage(item.imageUrl)
        foodRecipeItemContainer.setOnClickListener { listener.invoke(item.id) }
    }
}
