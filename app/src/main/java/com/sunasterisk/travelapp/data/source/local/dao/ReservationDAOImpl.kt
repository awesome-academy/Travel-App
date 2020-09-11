package com.sunasterisk.travelapp.data.source.local.dao

import com.sunasterisk.travelapp.data.models.Reservation
import com.sunasterisk.travelapp.data.models.User
import com.sunasterisk.travelapp.data.source.local.database.DatabaseHelper

class ReservationDAOImpl private constructor(database: DatabaseHelper) :
    ReservationDAO {
    private val database = database.writableDatabase

    override fun insertReservation(reservation: Reservation): Boolean {
        if (isExists(reservation)) return false
        return database.insert(
            Reservation.TABLE_NAME,
            null,
            reservation.getValues()
        ) > 0
    }

    override fun getReservations(): List<Reservation> {
        val cursor = database.query(Reservation.TABLE_NAME, null, null, null, null, null, null)
        val reservations = mutableListOf<Reservation>()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                reservations.add(Reservation(cursor))
                cursor.moveToNext()
            }
            cursor.close()
        }
        return reservations
    }

    override fun deleteReservation(reservation: Reservation): Boolean =
        database.delete(
            Reservation.TABLE_NAME,
            "${Reservation.ID} = ?",
            arrayOf(reservation.id.toString())
        ) > 0

    override fun updateReservation(reservation: Reservation): Boolean {
        if (!isExists(reservation)) return false
        return database.update(
            User.TABLE_NAME,
            reservation.getValues(),
            "${Reservation.ID} = ?",
            arrayOf(reservation.id.toString())
        ) > 0
    }

    private fun isExists(reservation: Reservation): Boolean {
        val cursor = database.query(
            Reservation.TABLE_NAME,
            null,
            "${Reservation.ID}=?",
            arrayOf(reservation.id.toString()),
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
        private var INSTANCE: ReservationDAO? = null

        fun getInstance(database: DatabaseHelper): ReservationDAO =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ReservationDAOImpl(database).also { INSTANCE = it }
            }
    }
}
