package com.minosai.local.conductor

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.minosai.local.passenger.PassengerDao
import com.minosai.local.passenger.PassengerDatabase
import com.minosai.model.Ticket

@Database(entities = [Ticket::class], version = 1, exportSchema = false)
abstract class ConductorDatabase: RoomDatabase() {

    abstract fun conductorDao() : ConductorDao

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ConductorDatabase::class.java,
                "conductor.db"
            ).build()
    }

}