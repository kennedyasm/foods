package com.example.foods.core

sealed class State {

    object Loading : State()
    data class Success(val result: Any) : State()
    data class Error(val throwable: Throwable) : State()

    companion object {

        private fun <T> Success.to(): T {
            @Suppress("UNCHECKED_CAST")
            return this.result as T
        }

        fun <T> State.checkActionBy(
            onSuccess: (T) -> Unit,
            onError: (Throwable) -> Unit,
            onLoading: () -> Unit = { },
        ) {
            when (this) {
                is Loading -> onLoading.invoke()
                is Success -> onSuccess.invoke(to())
                is Error -> onError.invoke(this.throwable)
            }
        }
    }
}