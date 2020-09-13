package com.sunasterisk.travelapp.data.source.local

import com.sunasterisk.travelapp.data.LoadDataAsync
import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Reservation
import com.sunasterisk.travelapp.data.source.ReservationDataSource
import com.sunasterisk.travelapp.data.source.local.dao.ReservationDAO

class ReservationLocalDatasource private constructor(private val resvationDAO: ReservationDAO) :
    ReservationDataSource.Local {

    override fun insertReservation(reservation: Reservation, callback: OnDataCallback<Boolean>) {
        LoadDataAsync<Reservation, Boolean>(callback) {
            resvationDAO.insertReservation(it)
        }.execute(reservation)
    }

    override fun getReservations(callback: OnDataCallback<List<Reservation>>) {
        LoadDataAsync<Unit, List<Reservation>>(callback) {
            resvationDAO.getReservations()
        }.execute(Unit)
    }

    override fun deleteReservation(reservation: Reservation, callback: OnDataCallback<Boolean>) {
        LoadDataAsync<Reservation, Boolean>(callback) {
            resvationDAO.deleteReservation(it)
        }.execute(reservation)
    }

    override fun updateReservation(reservation: Reservation, callback: OnDataCallback<Boolean>) {
        LoadDataAsync<Reservation, Boolean>(callback) {
            resvationDAO.updateReservation(it)
        }.execute(reservation)
    }

    companion object {
        @Volatile
        private var instance: ReservationLocalDatasource? = null

        fun getInstance(resvationDAO: ReservationDAO) =
            instance ?: synchronized(this) {
                instance ?: ReservationLocalDatasource(resvationDAO).also {
                    instance = it
                }
            }
    }
}
