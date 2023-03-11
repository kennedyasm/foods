package com.example.foods.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.foods.core.State
import com.example.foods.core.State.Companion.checkActionBy
import com.example.foods.core.extensions.getIntOrDefault
import com.example.foods.core.extensions.getStringOrDefault
import com.example.foods.core.extensions.loadImage
import com.example.foods.databinding.FragmentFoodRecipeDetailsBinding
import com.example.foods.domain.models.FoodRecipeDetailsUi
import com.example.foods.presentation.viewmodel.FoodRecipeDetailsViewModel
import javax.inject.Inject

class FoodRecipeDetails :
    BaseFragment<FragmentFoodRecipeDetailsBinding>(FragmentFoodRecipeDetailsBinding::inflate) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: FoodRecipeDetailsViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getFoodRecipeDetailsById()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        setupActionToolbar()
    }

    private fun setupActionToolbar() {
        requireActivity().setActionBar(binding.toolbar)
        requireActivity().actionBar?.setDisplayHomeAsUpEnabled(true)
        binding.collapsingToolbarLayout.title = getStringOrDefault(FOOD_RECIPE_NAME)
    }

    private fun initObservers() {
        viewModel.getFoodRecipeDetails.observe(viewLifecycleOwner, ::handleFoodRecipeDetailsState)
    }

    private fun handleFoodRecipeDetailsState(state: State) {
        state.checkActionBy(::foodRecipeDetails, ::foodRecipeDetailsError, ::showLoading)
    }

    private fun foodRecipeDetails(foodRecipeDetailsUi: FoodRecipeDetailsUi) {
        binding.foodRecipeImageDetails.loadImage(foodRecipeDetailsUi.imageUrl)
    }

    private fun foodRecipeDetailsError(throwable: Throwable) {

    }

    private fun showLoading() {

    }

    private fun hideLoading() {

    }

    private fun getFoodRecipeDetailsById() {
        viewModel.getFoodRecipeDetailsById(getIntOrDefault(FOOD_RECIPE_ID))
    }

    companion object {
        private const val FOOD_RECIPE_ID = "food_recipe_id"
        private const val FOOD_RECIPE_NAME = "food_recipe_name"
    }
}
