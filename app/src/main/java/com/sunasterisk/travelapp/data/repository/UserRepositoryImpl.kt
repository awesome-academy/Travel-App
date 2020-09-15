package com.sunasterisk.travelapp.data.repository

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.User
import com.sunasterisk.travelapp.data.source.UserDataSource

class UserRepositoryImpl private constructor(
    private val local: UserDataSource.Local
) : UserRepository {

    override fun addUser(user: User, callback: OnDataCallback<Boolean>) {
        local.addUser(user, callback)
    }

    override fun isUser(email: String, password: String, onDataCallback: OnDataCallback<User>) {
        local.isUser(email, password, onDataCallback)
    }

    override fun setCurrentUser(user: User) {
        local.setCurrentUser(user)
    }

    override fun getCurrentUser(): User = local.getCurrentUser()

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null

        fun getInstance(
            local: UserDataSource.Local
        ) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: UserRepositoryImpl(local).also { INSTANCE = it }
        }
    }
}
