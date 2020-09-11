package com.sunasterisk.travelapp.data.source.local.dao

import com.sunasterisk.travelapp.data.models.Reservation

interface ReservationDAO {
    fun insertReservation(reservation: Reservation): Boolean
    fun getReservations(): List<Reservation>
    fun deleteReservation(reservation: Reservation): Boolean
    fun updateReservation(reservation: Reservation): Boolean
}
