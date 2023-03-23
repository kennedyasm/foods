package com.example.foods.core

sealed class State {
    object Loading : State()
    data class Success(val result: Any) : State()
    data class Error(val throwable: Throwable) : State() {
        val message = "Error: ${throwable.message ?: "general error"}, intenta más tarde."
    }

    companion object {

        fun <T> Success.to(): T {
            @Suppress("UNCHECKED_CAST")
            return this.result as T
        }

        fun <T> State.checkActionBy(
            onSuccess: (T) -> Unit,
            onError: (String) -> Unit,
            onLoading: () -> Unit = { },
        ) {
            when (this) {
                is Loading -> onLoading.invoke()
                is Success -> onSuccess.invoke(to())
                is Error -> onError.invoke(this.message)
            }
        }
    }
}
