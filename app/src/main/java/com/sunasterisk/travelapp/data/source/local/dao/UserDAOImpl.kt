package com.sunasterisk.travelapp.data.source.local.dao

import com.sunasterisk.travelapp.data.models.User
import com.sunasterisk.travelapp.data.source.local.database.DatabaseHelper

class UserDAOImpl(database: DatabaseHelper) : UserDAO {
    private var database = database.writableDatabase

    override fun addUser(user: User): Boolean {
        if (isExists(user)) return false
        return database.insert(User.TABLE_NAME, null, user.getValues()) > 0
    }

    override fun isUser(email: String, password: String): User? {
        val cursor = database.query(
            User.TABLE_NAME,
            null,
            "${User.EMAIL}=? and ${User.PASSWORD} = ?",
            arrayOf(email, password),
            null,
            null,
            null
        )
        var user: User? = null
        cursor?.run {
            if (moveToFirst()) {

                user = User(cursor)
            }
            cursor.close()
        }
        return user
    }

    override fun updateUser(user: User): Boolean {
        if (isExists(user)) return false
        return database.update(
            User.TABLE_NAME,
            user.getValues(),
            "${User.ID} = ?",
            arrayOf(user.id.toString())
        ) > 0
    }

    private fun isExists(user: User): Boolean {
        val cursor = database.rawQuery(
            "select * from ${User.TABLE_NAME} where ${User.EMAIL}=?",
            arrayOf(user.email)
        )
        val result = cursor?.moveToFirst() ?: false
        cursor.close()
        return result
    }

    companion object {
        @Volatile
        private var INSTANCE: UserDAO? = null

        fun getInstance(database: DatabaseHelper): UserDAO =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserDAOImpl(database).also { INSTANCE = it }
            }
    }
}
