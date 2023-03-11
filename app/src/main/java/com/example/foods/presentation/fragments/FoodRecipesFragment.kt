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
import com.example.foods.domain.dto.FoodRecipeItemDTO
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
        state.checkActionBy(::handleFoodRecipesList, ::handleFoodRecipesError, ::showLoading)
    }

    private fun handleFoodRecipesList(list: List<FoodRecipeItemDTO>) {
        hideLoading()
        binding.totalResultsText.text = String.format(getString(R.string.total_results), list.size)
        foodRecipesAdapter?.setList(list)
    }

    private fun handleFoodRecipesError(throwable: Throwable) {
        hideLoading()
    }

    private fun showLoading() {

    }

    private fun hideLoading() {

    }

    private fun openFoodRecipeDetails(id: Int) {
        findNavController().navigate(buildDirectionToFoodRecipeDetails(id))
    }

    private fun buildDirectionToFoodRecipeDetails(id: Int): NavDirections {
        return FoodRecipesFragmentDirections.actionFoodRecipesFragmentToFoodRecipeDetails(id)
    }

}
