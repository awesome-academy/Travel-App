package com.sunasterisk.travelapp.di

import android.content.Context
import com.sunasterisk.travelapp.data.repository.*
import com.sunasterisk.travelapp.data.source.local.*
import com.sunasterisk.travelapp.data.source.local.dao.BookingDAOImpl
import com.sunasterisk.travelapp.data.source.local.dao.LocationFavouriteDAOImpl
import com.sunasterisk.travelapp.data.source.local.dao.ReservationDAOImpl
import com.sunasterisk.travelapp.data.source.local.dao.UserDAOImpl
import com.sunasterisk.travelapp.data.source.local.database.DatabaseHelper
import com.sunasterisk.travelapp.data.source.local.preferences.PreferencesHelperImpl
import com.sunasterisk.travelapp.data.source.remote.HotelRemoteDatasource
import com.sunasterisk.travelapp.data.source.remote.LocationRemoteDatasource
import com.sunasterisk.travelapp.data.source.remote.RestaurantRemoteDatasource

object Injector {
    fun getBookingRepository(context: Context): BookingRepository {
        val database = DatabaseHelper.getInstance(context)
        val bookingDAO = BookingDAOImpl.getInstance(database)
        val local = BookingLocalDatasource.getInstance(bookingDAO)
        return BookingRepositoryImpl.getInstance(local)
    }

    fun getReservationRepository(context: Context): ReservationRepository {
        val database = DatabaseHelper.getInstance(context)
        val reservationDAO = ReservationDAOImpl.getInstance(database)
        val local = ReservationLocalDatasource.getInstance(reservationDAO)
        return ReservationRepositoryImpl.getInstance(local)
    }

    fun getUserRepository(context: Context): UserRepository {
        val database = DatabaseHelper.getInstance(context)
        val userDAO = UserDAOImpl.getInstance(database)
        val pref = PreferencesHelperImpl.getInstance(context)
        val local = UserLocalDatasource.getInstance(userDAO, pref)
        return UserRepositoryImpl.getInstance(local)
    }

    fun getLocationRepository(context: Context): LocationRepository {
        val database = DatabaseHelper.getInstance(context)
        val locationDAO = LocationFavouriteDAOImpl.getInstance(database)
        val pref = PreferencesHelperImpl.getInstance(context)
        val local = LocationLocalDatasource.getInstance(locationDAO, pref)
        val remote = LocationRemoteDatasource.getInstance()
        return LocationRepositoryImpl.getInstance(local, remote)
    }

    fun getHotelRepository(context: Context): HotelRepository {
        val database = DatabaseHelper.getInstance(context)
        val locationDAO = LocationFavouriteDAOImpl.getInstance(database)
        val pref = PreferencesHelperImpl.getInstance(context)
        val local = HotelLocalDatasource.getInstance(locationDAO, pref)
        val remote = HotelRemoteDatasource.getInstance()
        return HotelRepositoryImpl.getInstance(local, remote)
    }

    fun getRestaurantRepository(context: Context): RestaurantRepository {
        val database = DatabaseHelper.getInstance(context)
        val locationDAO = LocationFavouriteDAOImpl.getInstance(database)
        val pref = PreferencesHelperImpl.getInstance(context)
        val local = RestaurantLocalDatasource.getInstance(locationDAO, pref)
        val remote = RestaurantRemoteDatasource.getInstance()
        return RestaurantRepositoryImpl.getInstance(local, remote)
    }
}
