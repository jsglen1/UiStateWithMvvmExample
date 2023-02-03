package com.example.uistatewithmvvmexample.flowlearning.domain

import com.example.uistatewithmvvmexample.flowlearning.data.UserRepository
import com.example.uistatewithmvvmexample.flowlearning.data.model.User
import javax.inject.Inject

class GetUsers @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(): List<User> {
        val Users = repository.getUsers()

        return if (Users.isNotEmpty()) {
            Users
        } else {
            emptyList()
        }

    }


}

