package com.example.presentation.home

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.core.extensions.binding
import com.example.domain.models.FoodRecipeItemUi
import com.example.presentation.databinding.FoodRecipeItemViewBinding
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class FoodRecipesAdapter @Inject constructor() : RecyclerView.Adapter<FoodRecipeViewHolder>() {

    private var foodRecipesList = mutableListOf<FoodRecipeItemUi>()
    private var listener: ((FoodRecipeItemUi) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<FoodRecipeItemUi>) {
        foodRecipesList.clear()
        foodRecipesList.addAll(list)
        notifyDataSetChanged()
    }

    fun setListener(listener: (FoodRecipeItemUi) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodRecipeViewHolder {
        return FoodRecipeViewHolder(parent.binding(FoodRecipeItemViewBinding::inflate), listener)
    }

    override fun getItemCount(): Int = foodRecipesList.size

    override fun onBindViewHolder(holder: FoodRecipeViewHolder, position: Int) {
        holder.bind(foodRecipesList[position])
    }
}
