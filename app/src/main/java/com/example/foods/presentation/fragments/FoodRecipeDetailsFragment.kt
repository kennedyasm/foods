package com.example.foods.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.foods.R
import com.example.foods.core.State
import com.example.foods.core.State.Companion.checkActionBy
import com.example.foods.core.State.Companion.to
import com.example.foods.core.extensions.getIntOrDefault
import com.example.foods.core.extensions.getStringOrDefault
import com.example.foods.core.extensions.loadImage
import com.example.foods.databinding.FragmentFoodRecipeDetailsBinding
import com.example.foods.domain.models.FoodRecipeDetailsUi
import com.example.foods.presentation.adapters.RecipePreparationIngredientsAdapter
import com.example.foods.presentation.viewmodel.FoodRecipeDetailsViewModel
import javax.inject.Inject

class FoodRecipeDetailsFragment :
    BaseFragment<FragmentFoodRecipeDetailsBinding>(FragmentFoodRecipeDetailsBinding::inflate) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var preparationIngredientsAdapter: RecipePreparationIngredientsAdapter
    private val viewModel: FoodRecipeDetailsViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdapter()
        getFoodRecipeDetailsById()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        setupActionToolbar()
        setupSeeMapClickListener()
    }

    private fun initAdapter() {
        preparationIngredientsAdapter = RecipePreparationIngredientsAdapter(requireContext())
    }

    private fun setupActionToolbar() {
        requireActivity().setActionBar(binding.toolbar)

        binding.collapsingToolbarLayout.title = getStringOrDefault(FOOD_RECIPE_NAME)
    }

    private fun setupSeeMapClickListener() {
        binding.seeMapLocation.setOnClickListener {
            val state = (viewModel.getFoodRecipeDetails.value as? State.Success)
            state?.to<FoodRecipeDetailsUi>()?.let {
                val direction = FoodRecipeDetailsFragmentDirections.toFoodRecipeOriginMapFragment(
                    getStringOrDefault(FOOD_RECIPE_NAME),
                    it.latitude.toString(),
                    it.longitude.toString()
                )
                findNavController().navigate(direction)
            }
        }
    }

    private fun initObservers() {
        viewModel.getFoodRecipeDetails.observe(viewLifecycleOwner, ::handleFoodRecipeDetailsState)
    }

    private fun handleFoodRecipeDetailsState(state: State) {
        state.checkActionBy(::foodRecipeDetails, ::foodRecipeDetailsError, ::showLoading)
    }

    private fun foodRecipeDetails(item: FoodRecipeDetailsUi) = binding.run {
        hideLoading()
        foodRecipeImageDetails.loadImage(item.imageUrl)
        foodRecipeDescription.text = item.description
        foodRecipeOrigin.text = String.format(getString(R.string.origin), item.origin)

        preparationIngredientsAdapter.setItems(
            getPreparationIngredientsTitleList(),
            getPreparationIngredientsHashMap(item)
        )

        preparationIngredientsExpandableList.setAdapter(preparationIngredientsAdapter)
    }

    private fun foodRecipeDetailsError(errorMessage: String) {
        hideLoading()
        showRetrySnackBar(binding.root, errorMessage, ::getFoodRecipeDetailsById)
    }

    private fun showLoading() {
        binding.linearProgressIndicator.show()
    }

    private fun hideLoading() {
        binding.linearProgressIndicator.hide()
    }

    private fun getPreparationIngredientsTitleList() =
        listOf(getString(R.string.ingredients_label), getString(R.string.preparation_steps_label))

    private fun getPreparationIngredientsHashMap(foodRecipeDetailsUi: FoodRecipeDetailsUi) =
        HashMap<String, List<String>>().apply {
            this[getString(R.string.ingredients_label)] = foodRecipeDetailsUi.ingredients
            this[getString(R.string.preparation_steps_label)] = foodRecipeDetailsUi.preparation
        }

    private fun getFoodRecipeDetailsById() =
        viewModel.getFoodRecipeDetailsById(getIntOrDefault(FOOD_RECIPE_ID))

    companion object {
        private const val FOOD_RECIPE_ID = "food_recipe_id"
        const val FOOD_RECIPE_NAME = "food_recipe_name"
    }
}
