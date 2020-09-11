package com.sunasterisk.travelapp.data.source.local.dao

import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.data.models.User
import com.sunasterisk.travelapp.data.source.local.database.DatabaseHelper

class LocationFavouriteDAOImpl private constructor(databaseHelper: DatabaseHelper) :
    LocationFavouriteDAO {
    private val database = databaseHelper.writableDatabase

    override fun insertLocationFavourite(location: Location, user: User): Boolean {
        if (isExist(location, user)) return false
        return database.insert(
            Location.TABLE_NAME,
            null,
            location.getValue(user)
        ) > 0
    }

    override fun getAllLocationsFavourite(user: User): List<Location> {
        val cursor = database.query(
            Location.TABLE_NAME,
            null,
            "${User.ID} = ?",
            arrayOf(user.id.toString()),
            null,
            null,
            null
        )
        val locations = mutableListOf<Location>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                locations.add(Location(cursor))
                cursor.moveToNext()
            }
        }
        cursor.close()
        return locations
    }

    override fun deleteLocationFavourite(location: Location, user: User): Boolean =
        database.delete(
            Location.TABLE_NAME,
            "${Location.ID} = ? and ${User.ID} = ?",
            arrayOf(location.id, user.id.toString())
        ) > 0

    private fun isExist(location: Location, user: User): Boolean {
        val cursor = database.query(
            Location.TABLE_NAME,
            null,
            "${Location.ID} = ? and ${User.ID} = ?",
            arrayOf(location.id, user.id.toString()),
            null,
            null,
            null
        )
        val result = cursor.moveToFirst()
        cursor.close()
        return result
    }

    companion object {
        @Volatile
        private var INSTANCE: LocationFavouriteDAO? = null

        fun getInstance(database: DatabaseHelper): LocationFavouriteDAO =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocationFavouriteDAOImpl(database).also { INSTANCE = it }
            }
    }
}
