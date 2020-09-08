package com.sunasterisk.travelapp.data.models

data class User(
    var id: Int = -1,
    val name: String,
    val email: String,
    var password: String,
    var phone: String
)
