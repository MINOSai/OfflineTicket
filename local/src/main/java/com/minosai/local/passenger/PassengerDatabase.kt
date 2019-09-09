package com.minosai.local.passenger

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.minosai.model.Ticket

@Database(entities = [Ticket::class], version = 1, exportSchema = false)
abstract class PassengerDatabase : RoomDatabase() {

    abstract fun passengerDao() : PassengerDao

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PassengerDatabase::class.java,
                "passenger.db"
            ).build()
    }

}