package com.sunasterisk.travelapp.data.source

import com.sunasterisk.travelapp.data.models.User

interface UserDataSource {
    interface Local {
        fun addUser(user: User): Boolean
        fun isUser(email: String, password: String): User?
    }
}
