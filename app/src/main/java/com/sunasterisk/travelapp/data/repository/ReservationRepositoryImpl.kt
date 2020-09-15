package com.sunasterisk.travelapp.data.repository

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Reservation
import com.sunasterisk.travelapp.data.source.ReservationDataSource

class ReservationRepositoryImpl private constructor(
    private val local: ReservationDataSource.Local
) : ReservationRepository {

    override fun insertReservation(reservation: Reservation, callback: OnDataCallback<Boolean>) {
        local.insertReservation(reservation, callback)
    }

    override fun getReservations(callback: OnDataCallback<List<Reservation>>) {
        local.getReservations(callback)
    }

    override fun deleteReservation(reservation: Reservation, callback: OnDataCallback<Boolean>) {
        local.deleteReservation(reservation, callback)
    }

    override fun updateReservation(reservation: Reservation, callback: OnDataCallback<Boolean>) {
        local.updateReservation(reservation, callback)
    }

    companion object {
        @Volatile
        private var INSTANCE: ReservationRepository? = null

        fun getInstance(
            local: ReservationDataSource.Local
        ) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: ReservationRepositoryImpl(local).also { INSTANCE = it }
        }
    }
}
