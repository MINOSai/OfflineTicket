package com.minosai.local.passenger

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.minosai.local.util.BaseDao
import com.minosai.model.Ticket

@Dao
abstract class PassengerDao : BaseDao<Ticket>() {

    @Query("SELECT * FROM ticket")
    abstract fun getAllTickets() : LiveData<List<Ticket>>

    suspend fun save(tickets: List<Ticket>) {
        insert(tickets)
    }
}