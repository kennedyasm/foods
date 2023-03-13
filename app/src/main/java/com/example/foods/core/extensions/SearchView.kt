package com.example.foods.core.extensions

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal fun SearchView.queries(
    lifecycle: Lifecycle,
    mQuery: (textChange: String?) -> Unit
) {
    var stateJob: Job? = null
    val coroutineScope = lifecycle.coroutineScope
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            mQuery(query)
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            stateJob?.cancel()
            stateJob = coroutineScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    newText?.let {
                        delay(500)
                        mQuery(it)
                    }
                }
            }
            return true
        }
    })
}
