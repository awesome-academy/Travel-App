package com.sunasterisk.travelapp.data.source.local

import com.sunasterisk.travelapp.data.LoadDataAsync
import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.User
import com.sunasterisk.travelapp.data.source.UserDataSource
import com.sunasterisk.travelapp.data.source.local.dao.UserDAO
import com.sunasterisk.travelapp.data.source.local.preferences.PreferencesHelper

class UserLocalDatasource private constructor(
    private val userDAO: UserDAO,
    private val preference: PreferencesHelper
) : UserDataSource.Local {

    override fun addUser(user: User, callback: OnDataCallback<Boolean>) {
        LoadDataAsync<User, Boolean>(callback) {
            userDAO.addUser(it)
        }.execute(user)
    }

    override fun isUser(email: String, password: String, callback: OnDataCallback<User>) {
        LoadDataAsync<Unit, User>(callback) {
            userDAO.isUser(email, password)
        }.execute(Unit)
    }

    override fun setCurrentUser(user: User) {
        preference.setCurrentUser(user)
    }

    companion object {
        @Volatile
        private var instance: UserLocalDatasource? = null

        fun getInstance(userDAO: UserDAO, preference: PreferencesHelper) =
            instance ?: synchronized(this) {
                instance ?: UserLocalDatasource(userDAO, preference).also {
                    instance = it
                }
            }
    }
}
