package com.sunasterisk.travelapp.data.source.local.preferences

import com.sunasterisk.travelapp.data.models.Setting
import com.sunasterisk.travelapp.data.models.User

interface PreferencesHelper {
    fun setCurrentUser(user: User)
    fun getCurrentUser(): User
    fun clearUser()
    fun setSetting(setting: Setting)
    fun getSetting(): Setting
}
