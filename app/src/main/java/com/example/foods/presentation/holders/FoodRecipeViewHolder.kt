package com.example.foods.presentation.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.foods.core.extensions.loadImage
import com.example.foods.databinding.FoodRecipeItemViewBinding
import com.example.foods.domain.models.FoodRecipeItemUi

class FoodRecipeViewHolder(
    private val binding: FoodRecipeItemViewBinding,
    private val listener: ((FoodRecipeItemUi) -> Unit)?
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FoodRecipeItemUi) = binding.run {
        foodRecipeTitle.text = item.name
        foodRecipeImage.loadImage(item.imageUrl)
        foodRecipeItemContainer.setOnClickListener { listener?.invoke(item) }
    }
}
