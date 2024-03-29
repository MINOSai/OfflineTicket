package com.minosai.feature_conductor.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.minosai.common.extensions.toast
import com.minosai.feature_conductor.R
import com.minosai.feature_conductor.home.views.TicketAdapter
import com.minosai.model.Result
import kotlinx.android.synthetic.main.conductor_home_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ConductorHomeFragment : Fragment() {

    private val viewModel by viewModel<ConductorHomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.conductor_home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        home_button_new_ticket.setOnClickListener {
            findNavController()
                .navigate(R.id.action_conductorHomeFragment_to_receiveDetailsFragment)
        }

        home_button_upload_tickets.setOnClickListener {
            viewModel.uploadTickets().observe(this, Observer { result ->
                when (result.status) {
                    Result.Status.LOADING -> toast("Uploading tickets...")
                    Result.Status.SUCCESS -> toast("Tickets uploaded successfully")
                    Result.Status.ERROR -> toast(result.message!!)
                }
            })
        }

        val ticketAdapter = TicketAdapter()
        rv_conductor_home.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ticketAdapter
        }

        viewModel.getTickets().observe(this, Observer { tickets ->
            ticketAdapter.update(tickets)
        })
    }

}
