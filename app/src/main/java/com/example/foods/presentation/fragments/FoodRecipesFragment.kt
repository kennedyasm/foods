package com.example.foods.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
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
import com.example.foods.core.extensions.getSearchViewQueryTextListener
import com.example.foods.core.extensions.hideKeyBoard
import com.example.foods.databinding.FragmentFoodRecipesBinding
import com.example.foods.domain.models.FoodRecipeItemUi
import com.example.foods.presentation.adapters.FoodRecipesAdapter
import com.example.foods.presentation.viewmodel.FoodRecipesViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch
import javax.inject.Inject

class FoodRecipesFragment :
    BaseFragment<FragmentFoodRecipesBinding>(FragmentFoodRecipesBinding::inflate) {

    @Inject
    lateinit var foodRecipesAdapter: FoodRecipesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: FoodRecipesViewModel by viewModels { viewModelFactory }

    private var queryListener: SearchView.OnQueryTextListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFoodRecipesAdapterListener()
        startFoodRecipeStateFlow()
        startSearchQueryTextStateFlow()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindingViews()
    }

    private fun initFoodRecipesAdapterListener() {
        foodRecipesAdapter.setListener(::openFoodRecipeDetails)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initBindingViews() = binding.run {
        foodRecipesRecyclerView.run {
            adapter = foodRecipesAdapter
            setOnTouchListener { _, _ -> root.requestFocus(); false }
        }
        searchView.run {
            setOnQueryTextListener(queryListener)
            findViewById<SearchAutoComplete>(androidx.appcompat.R.id.search_src_text)
                .setOnFocusChangeListener { searchEditText, hasFocus ->
                    if (hasFocus.not()) searchEditText.hideKeyBoard()
                }
        }
    }

    private fun getFoodRecipes() = viewModel.getFoodRecipes()

    private fun startFoodRecipeStateFlow() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getFoodRecipesState.collectLatest(::handleFoodRecipesState)
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun startSearchQueryTextStateFlow() {
        queryListener = getSearchViewQueryTextListener { stateFlowQueryText ->
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    stateFlowQueryText.drop(SKIP_FLOW_TIMES).debounce(DEBOUNCE_TIME)
                        .collectLatest(::getFoodRecipesByQuery)
                }
            }
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
        foodRecipesAdapter.setList(list)
    }

    private fun foodRecipesError(errorMessage: String) {
        enableSearchView(false)
        hideLoading()
        showRetrySnackBar(binding.root, errorMessage, ::getFoodRecipes)
    }

    private fun showLoading() {
        binding.linearProgressIndicator.show()
    }

    private fun enableSearchView(isEnabledSearchView: Boolean) {
        // binding.searchView.findViewById<SearchAutoComplete>(androidx.appcompat.R.id.search_src_text)
        //   .run { isEnabled = isEnabledSearchView }
    }

    private fun hideLoading() = binding.linearProgressIndicator.hide()

    private fun openFoodRecipeDetails(item: FoodRecipeItemUi) {
        findNavController().navigate(buildDirectionToFoodRecipeDetails(item))
    }

    private fun buildDirectionToFoodRecipeDetails(item: FoodRecipeItemUi): NavDirections {
        return FoodRecipesFragmentDirections.toFoodRecipeDetails(item.id, item.name)
    }

    override fun onDestroy() {
        queryListener = null
        //  binding.searchView.setOnQueryTextListener(null)
        super.onDestroy()
    }

    companion object {
        private const val SKIP_FLOW_TIMES = 1
        const val DEBOUNCE_TIME = 600L
    }
}
