package com.minosai.feature_conductor.home

import com.minosai.common.base.BaseViewModel
import com.minosai.repository.ConductorRepository

class ConductorHomeViewModel (private val repo: ConductorRepository) : BaseViewModel() {
    // TODO: Implement the ViewModel

    fun getTickets() = repo.getAllTickets()
}
