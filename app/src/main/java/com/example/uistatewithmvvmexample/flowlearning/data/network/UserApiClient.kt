package com.example.uistatewithmvvmexample.flowlearning.data.network

import com.example.uistatewithmvvmexample.flowlearning.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface UserApiClient {

    @GET("/users")
    suspend fun getAllUsers(): Response<List<User>>

}