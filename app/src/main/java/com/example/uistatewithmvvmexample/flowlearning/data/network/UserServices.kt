package com.example.uistatewithmvvmexample.flowlearning.data.network

import com.example.uistatewithmvvmexample.flowlearning.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserServices @Inject constructor(private val api:UserApiClient) {

    suspend fun getUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllUsers()
            response.body() ?: emptyList()
        }
    }

}