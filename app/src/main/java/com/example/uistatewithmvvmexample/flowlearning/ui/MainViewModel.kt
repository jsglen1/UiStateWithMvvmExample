package com.example.uistatewithmvvmexample.flowlearning.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uistatewithmvvmexample.flowlearning.domain.GetUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val getUsers: GetUsers) : ViewModel() {

    val state = MutableStateFlow<State>(State.START)

    init {
        loadUser()
    }

    private fun loadUser() = viewModelScope.launch {
        state.value = State.LOADING
        try {
            val users = withContext(Dispatchers.IO) { getUsers() }
            state.value = State.SUCCESS(users)
        } catch (e: Exception) {
            state.value = e.localizedMessage?.let { State.FAILURE(it) }!!
        }
    }

}