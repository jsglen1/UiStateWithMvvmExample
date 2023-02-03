package com.example.uistatewithmvvmexample.flowlearning.ui

import com.example.uistatewithmvvmexample.flowlearning.data.model.User

sealed class State {
    object START : State()
    object LOADING : State()
    data class SUCCESS(val users: List<User>) : State()
    data class FAILURE(val message: String) : State()
}
