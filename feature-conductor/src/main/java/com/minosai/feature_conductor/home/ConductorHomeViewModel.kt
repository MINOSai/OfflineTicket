package com.minosai.feature_conductor.home

import com.minosai.common.base.BaseViewModel
import com.minosai.repository.ConductorRepository

class ConductorHomeViewModel (private val repo: ConductorRepository) : BaseViewModel() {

    fun getTickets() = repo.getTicketsFromDb()

    fun uploadTickets() = repo.uploadTickets()
}
