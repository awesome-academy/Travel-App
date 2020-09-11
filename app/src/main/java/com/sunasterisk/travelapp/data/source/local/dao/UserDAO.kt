package com.sunasterisk.travelapp.data.source.local.dao

import com.sunasterisk.travelapp.data.models.User

interface UserDAO {
    fun addUser(user: User): Boolean
    fun isUser(email: String, password: String): User?
    fun updateUser(user: User): Boolean
}
