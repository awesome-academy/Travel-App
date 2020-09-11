package com.sunasterisk.travelapp.data.source

import com.sunasterisk.travelapp.data.models.Reservation

interface ReservationDataSource {
    interface Local {
        fun insertReservation(reservation: Reservation): Boolean
        fun getReservations(): List<Reservation>
        fun deleteReservation(reservation: Reservation): Boolean
    }
}
