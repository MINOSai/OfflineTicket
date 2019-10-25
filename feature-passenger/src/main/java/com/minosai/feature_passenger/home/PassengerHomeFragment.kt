package com.minosai.feature_passenger.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.minosai.common.base.BaseFragment
import com.minosai.common.base.BaseViewModel
import com.minosai.common.extensions.toast
import com.minosai.feature_passenger.R
import com.minosai.feature_passenger.home.views.TicketAdapter
import com.minosai.model.Result
import kotlinx.android.synthetic.main.passenger_home_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class PassengerHomeFragment : BaseFragment() {

    private val viewModel: PassengerHomeViewModel by viewModel()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.passenger_home_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val balance = viewModel.getBalance().toString()
        home_text_balance.text = "Balance: $balance"

        home_button_add_balance.setOnClickListener {
            findNavController().navigate(R.id.action_passengerHomeFragment_to_balanceFragment)
        }

        home_button_buy_ticket.setOnClickListener {
            findNavController().navigate(R.id.action_passengerHomeFragment_to_sendDetailsFragment)
        }

        val ticketAdapter = TicketAdapter()
        rv_passenger_home.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ticketAdapter
        }

        viewModel.fetchAllTickets().observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> ticketAdapter.update(result.data ?: listOf())
                Result.Status.ERROR -> {
                    //TODO
                    toast("An error occurred")
                }
                Result.Status.LOADING -> {
                    //TODO
                    toast("Fetching tickets...")
                }
            }
        })
    }
}
