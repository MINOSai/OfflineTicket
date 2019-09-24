package com.minosai.feature_conductor.ticket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.minosai.common.Constants
import com.minosai.common.base.BaseChirpFragment
import com.minosai.common.base.BaseFragment
import com.minosai.common.base.BaseViewModel
import com.minosai.common.toast
import com.minosai.feature_conductor.R
import com.minosai.feature_conductor.ticket.ConductorTicketViewModel
import io.chirp.connect.ChirpConnect
import io.chirp.connect.models.ChirpError
import kotlinx.android.synthetic.main.fragment_send_ticket.*
import kotlinx.android.synthetic.main.fragment_send_ticket.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.lang.Exception

class SendTicketFragment : BaseChirpFragment() {

    private val viewModel by sharedViewModel<ConductorTicketViewModel>()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_send_ticket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.send_ticket_button_send.setOnClickListener {
            sendTicket()
        }
    }

    private fun sendTicket() {
        val ticketDetails = listOf(
            send_ticket_input_source.text.toString(),
            send_ticket_input_destination.text.toString(),
            send_ticket_input_amount.text.toString()
        )

        val ticketJson = Gson().toJson(ticketDetails)
        val payload = ticketJson.toByteArray()

        val error = chirpSend.send(payload)
        if (error.code > 0) {
            showErrorMessage()
        } else {
            requireContext().toast("Details sent!")
            send_ticket_text_placeholder.text = "Details sent successfully!"
        }
    }

    private fun showErrorMessage() {
        send_ticket_text_placeholder.text = "An error occurred. Please try again"
    }

}
