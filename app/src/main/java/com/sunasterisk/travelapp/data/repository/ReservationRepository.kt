package com.sunasterisk.travelapp.data.repository

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Reservation

interface ReservationRepository {

    fun insertReservation(reservation: Reservation, callback: OnDataCallback<Boolean>)

    fun getReservations(callback: OnDataCallback<List<Reservation>>)

    fun deleteReservation(reservation: Reservation, callback: OnDataCallback<Boolean>)

    fun updateReservation(reservation: Reservation, callback: OnDataCallback<Boolean>)
}
