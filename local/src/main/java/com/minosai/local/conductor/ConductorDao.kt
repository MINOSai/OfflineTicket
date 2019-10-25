package com.minosai.local.conductor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.minosai.local.util.BaseDao
import com.minosai.model.Ticket

@Dao
abstract class ConductorDao : BaseDao<Ticket>() {

    @Query("SELECT * FROM ticket")
    abstract fun getTicketsToUpload() : List<Ticket>

    @Query("SELECT * FROM ticket")
    abstract fun getAllTickets() : LiveData<List<Ticket>>

    suspend fun save(tickets: List<Ticket>) {
        insert(tickets)
    }

    suspend fun save(ticket: Ticket) {
        insert(ticket)
    }
}