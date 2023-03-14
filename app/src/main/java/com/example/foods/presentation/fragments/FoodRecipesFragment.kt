package com.example.foods.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.SearchAutoComplete
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.foods.R
import com.example.foods.core.State
import com.example.foods.core.State.Companion.checkActionBy
import com.example.foods.core.extensions.queries
import com.example.foods.databinding.FragmentFoodRecipesBinding
import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.presentation.adapters.FoodRecipesAdapter
import com.example.foods.presentation.viewmodel.FoodRecipesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class FoodRecipesFragment :
    BaseFragment<FragmentFoodRecipesBinding>(FragmentFoodRecipesBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: FoodRecipesViewModel by viewModels { viewModelFactory }
    private var foodRecipesAdapter: FoodRecipesAdapter? = null
    private var queryListener: SearchView.OnQueryTextListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFoodRecipesAdapter()
        initObservers()
        initObserverSearchViewQuery()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindingViews()
    }

    private fun initFoodRecipesAdapter() {
        foodRecipesAdapter = FoodRecipesAdapter(::openFoodRecipeDetails)
    }

    private fun initBindingViews() = binding.run {
        foodRecipesRecyclerView.adapter = foodRecipesAdapter
        searchView.setOnQueryTextListener(queryListener)
    }

    private fun getFoodRecipes() = viewModel.getFoodRecipes()

    private fun initObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getFoodRecipesState.collectLatest {
                    handleFoodRecipesState(it)
                }
            }
        }
    }

    private fun initObserverSearchViewQuery() {
        queryListener = queries(lifecycle) { query ->
            Log.d("kTest -> ","query: $query")
            query?.let(::getFoodRecipesByQuery)
        }
    }

    private fun getFoodRecipesByQuery(query: String) {
        viewModel.getFoodRecipesByQuery(query)
    }

    private fun handleFoodRecipesState(state: State) {
        state.checkActionBy(::foodRecipesList, ::foodRecipesError, ::showLoading)
    }

    private fun foodRecipesList(list: List<FoodRecipeItemUi>) {
        enableSearchView(true)
        hideLoading()
        binding.totalResultsText.text = String.format(getString(R.string.results), list.size)
        foodRecipesAdapter?.setList(list)
    }

    private fun foodRecipesError(throwable: Throwable) {
        enableSearchView(false)
        hideLoading()
        val message = String.format(getString(R.string.error), throwable.message)
        showRetrySnackBar(binding.root, message, ::getFoodRecipes)
    }

    private fun showLoading() {
        binding.linearProgressIndicator.show()
    }

    private fun enableSearchView(isEnabledSearchView: Boolean) {
        binding.searchView.findViewById<SearchAutoComplete>(androidx.appcompat.R.id.search_src_text)
            .run { isEnabled = isEnabledSearchView }
    }

    private fun hideLoading() = binding.linearProgressIndicator.hide()

    private fun openFoodRecipeDetails(item: FoodRecipeItemUi) {
        findNavController().navigate(buildDirectionToFoodRecipeDetails(item))
    }

    private fun buildDirectionToFoodRecipeDetails(item: FoodRecipeItemUi): NavDirections {
        return FoodRecipesFragmentDirections.toFoodRecipeDetails(item.id, item.name)
    }

    override fun onDestroy() {
        super.onDestroy()
        foodRecipesAdapter = null
    }
}
