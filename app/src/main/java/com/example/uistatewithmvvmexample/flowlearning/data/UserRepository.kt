package com.example.uistatewithmvvmexample.flowlearning.data

import com.example.uistatewithmvvmexample.flowlearning.data.model.User
import com.example.uistatewithmvvmexample.flowlearning.data.network.UserServices
import javax.inject.Inject

class UserRepository @Inject constructor(private val api:UserServices) {

    suspend fun getUsers(): List<User> {
        val response: List<User> = api.getUsers()
        return response.map { it }
    }
}