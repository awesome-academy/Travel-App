package com.sunasterisk.travelapp.data.source.local.dao

import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.data.models.User

interface LocationFavouriteDAO {
    fun insertLocationFavourite(location: Location, user: User): Boolean
    fun getAllLocationsFavourite(user: User): List<Location>
    fun deleteLocationFavourite(location: Location, user: User): Boolean
}
