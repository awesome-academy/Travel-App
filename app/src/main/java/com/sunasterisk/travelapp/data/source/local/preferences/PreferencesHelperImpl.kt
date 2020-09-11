package com.sunasterisk.travelapp.data.source.local.preferences

import android.content.Context
import com.sunasterisk.travelapp.data.models.Setting
import com.sunasterisk.travelapp.data.models.Setting.Companion.DOLAR
import com.sunasterisk.travelapp.data.models.Setting.Companion.ENGLISH
import com.sunasterisk.travelapp.data.models.User

class PreferencesHelperImpl private constructor(context: Context) : PreferencesHelper {

   private val preferencesUser = context.getSharedPreferences(
        PREF_NAME_USER,
        Context.MODE_PRIVATE
    )
   private val editorUser = preferencesUser.edit()
   private val preferencesSetting = context.getSharedPreferences(
        PREF_NAME_SETTING,
        Context.MODE_PRIVATE
    )
    private val editorSetting = preferencesSetting.edit()

    override fun setCurrentUser(user: User) {
        editorUser.apply {
            putInt(PREF_KEY_USER_ID, user.id)
            putString(PREF_KEY_USER_NAME, user.name)
            putString(PREF_KEY_USER_EMAIL, user.email)
            putString(PREF_KEY_USER_PHONE, user.phone)
            apply()
        }
    }

    override fun getCurrentUser(): User {
        preferencesUser.apply {
            return User(
                getInt(PREF_KEY_USER_ID, -1),
                getString(PREF_KEY_USER_NAME, "") ?: "",
                getString(PREF_KEY_USER_EMAIL, "") ?: "",
                "",
                getString(PREF_KEY_USER_PHONE, "") ?: ""
            )
        }
    }

    override fun clearUser() {
        editorUser.apply {
            putInt(PREF_KEY_USER_ID, -1)
            putString(PREF_KEY_USER_NAME, "")
            putString(PREF_KEY_USER_EMAIL, "")
            putString(PREF_KEY_USER_PHONE, "")
            apply()
        }
    }

    override fun setSetting(setting: Setting) {
         editorSetting.apply {
            putString(PREF_KEY_SETTING_LANGUAGE, setting.language)
            putString(PREF_KEY_SETTING_CURRENCY, setting.currency)
            apply()
        }
    }

    override fun getSetting(): Setting {
        preferencesSetting.apply {
            return Setting(
                getString(PREF_KEY_SETTING_LANGUAGE,ENGLISH) ?: ENGLISH,
                getString(PREF_KEY_SETTING_CURRENCY,DOLAR) ?: DOLAR
            )
        }
    }

    companion object {
        const val PREF_NAME_USER = "user"
        const val PREF_KEY_USER_ID = "user_id"
        const val PREF_KEY_USER_NAME = "name"
        const val PREF_KEY_USER_EMAIL = "email"
        const val PREF_KEY_USER_PHONE = "phone"

        const val PREF_NAME_SETTING = "setting"
        const val PREF_KEY_SETTING_LANGUAGE = "language"
        const val PREF_KEY_SETTING_CURRENCY = "currency"

        @Volatile
        private var INSTANCE: PreferencesHelperImpl? = null

        fun getInstance(context: Context): PreferencesHelperImpl =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: PreferencesHelperImpl(context).also { INSTANCE = it }
            }
    }
}
