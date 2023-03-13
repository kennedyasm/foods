package com.example.foods.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.foods.R
import com.example.foods.core.State
import com.example.foods.core.State.Companion.checkActionBy
import com.example.foods.databinding.FragmentFoodRecipesBinding
import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.presentation.adapters.FoodRecipesAdapter
import com.example.foods.presentation.viewmodel.FoodRecipesViewModel
import javax.inject.Inject

class FoodRecipesFragment :
    BaseFragment<FragmentFoodRecipesBinding>(FragmentFoodRecipesBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: FoodRecipesViewModel by viewModels { viewModelFactory }
    private var foodRecipesAdapter: FoodRecipesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFoodRecipesAdapter()
        getFoodRecipes()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapterInRecyclerView()
        initObservers()
    }

    private fun initFoodRecipesAdapter() {
        foodRecipesAdapter = FoodRecipesAdapter(::openFoodRecipeDetails)
    }

    private fun setAdapterInRecyclerView() {
        binding.foodRecipesRecyclerView.adapter = foodRecipesAdapter
    }

    private fun getFoodRecipes() = viewModel.getFoodRecipes()

    private fun initObservers() {
        viewModel.getFoodRecipesState.observe(viewLifecycleOwner, ::handleFoodRecipesState)
    }

    private fun handleFoodRecipesState(state: State) {
        state.checkActionBy(::foodRecipesList, ::foodRecipesError, ::showLoading)
    }

    private fun foodRecipesList(list: List<FoodRecipeItemUi>) {
        hideLoading()
        binding.totalResultsText.text = String.format(getString(R.string.results), list.size)
        foodRecipesAdapter?.setList(list)
    }

    private fun foodRecipesError(throwable: Throwable) {
        hideLoading()
        val message = String.format(getString(R.string.error), throwable.message)
        showRetrySnackBar(binding.root, message, ::getFoodRecipes)
    }

    private fun showLoading() = binding.linearProgressIndicator.show()

    private fun hideLoading() = binding.linearProgressIndicator.hide()

    private fun openFoodRecipeDetails(item: FoodRecipeItemUi) {
        findNavController().navigate(buildDirectionToFoodRecipeDetails(item))
    }

    private fun buildDirectionToFoodRecipeDetails(item: FoodRecipeItemUi): NavDirections {
        return FoodRecipesFragmentDirections.toFoodRecipeDetails(item.id, item.name)
    }
/*
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            val savedRecyclerLayoutState: Parcelable? =
                savedInstanceState.getParcelable("recycler_position")
            binding.foodRecipesRecyclerView.layoutManager?.onRestoreInstanceState(savedRecyclerLayoutState)
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("recycler_position", binding.foodRecipesRecyclerView.layoutManager?.onSaveInstanceState())
    }

 */
}
