package com.minosai.feature_conductor.home

import androidx.lifecycle.ViewModel
import com.minosai.repository.ConductorRepository

class ConductorHomeViewModel(private val repo: ConductorRepository) : ViewModel() {

    fun getTickets() = repo.getTicketsFromDb()

    fun uploadTickets() = repo.uploadTickets()
}
