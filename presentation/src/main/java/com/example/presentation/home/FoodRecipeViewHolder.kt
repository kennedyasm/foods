package com.example.presentation.home

import androidx.recyclerview.widget.RecyclerView
import com.example.core.core.extensions.loadImage
import com.example.domain.models.FoodRecipeItemUi
import com.example.presentation.databinding.FoodRecipeItemViewBinding

class FoodRecipeViewHolder(
    private val binding: FoodRecipeItemViewBinding,
    private val listener: ((FoodRecipeItemUi) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FoodRecipeItemUi) = binding.run {
        foodRecipeTitle.text = item.name
        foodRecipeImage.loadImage(item.imageUrl)
        foodRecipeItemContainer.setOnClickListener { listener?.invoke(item) }
    }
}
