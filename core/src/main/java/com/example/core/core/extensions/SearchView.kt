package com.example.core.core.extensions

import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal inline fun getSearchViewQueryTextListener(
    queryStateFlowListener: (queryStateFlow: StateFlow<String>) -> Unit
): SearchView.OnQueryTextListener {

    val stateFlow = MutableStateFlow("")
    queryStateFlowListener.invoke(stateFlow)

    val queryListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let { stateFlow.value = it }
            return true
        }
        override fun onQueryTextChange(newText: String?): Boolean {
            newText?.let { stateFlow.value = it }
            return true
        }
    }

    return queryListener
}
