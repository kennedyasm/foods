package com.example.foods.core.extensions

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal fun queries(
    lifecycle: Lifecycle,
    mQuery: (textChange: String?) -> Unit
): SearchView.OnQueryTextListener {

    var stateJob: Job? = null
    val coroutineScope = lifecycle.coroutineScope
    val queryListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            mQuery(query)
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            stateJob?.cancel()
            stateJob = coroutineScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    newText?.let {
                        delay(700)
                        mQuery(it)
                    }
                }
            }
            return true
        }
    }
    return queryListener
}
