package com.sunasterisk.travelapp.data.repository

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.User

interface UserRepository {

    fun addUser(user: User, callback: OnDataCallback<Boolean>)

    fun isUser(email: String, password: String, onDataCallback: OnDataCallback<User>)

    fun setCurrentUser(user: User)

    fun getCurrentUser(): User
}
