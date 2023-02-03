package com.example.uistatewithmvvmexample.flowlearning.data.model

//class User : ArrayList<UserItem>()

data class User(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)

