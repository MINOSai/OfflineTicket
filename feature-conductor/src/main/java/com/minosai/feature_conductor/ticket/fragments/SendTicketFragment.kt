package com.minosai.feature_conductor.ticket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.minosai.common.base.BaseChirpFragment
import com.minosai.common.base.BaseViewModel
import com.minosai.feature_conductor.ticket.ConductorTicketViewModel
import com.minosai.model.Ticket
import kotlinx.android.synthetic.main.fragment_send_ticket.*
import kotlinx.android.synthetic.main.fragment_send_ticket.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class SendTicketFragment : BaseChirpFragment() {

    private val viewModel by sharedViewModel<ConductorTicketViewModel>()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            com.minosai.feature_conductor.R.layout.fragment_send_ticket,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.send_ticket_button_send.setOnClickListener {
            sendTicket()
        }
    }

    private fun sendTicket() {

        val gson = Gson()
        val ints = listOf(1, 2, 3, 4, 5)

        // Serialization
        val json = gson.toJson(ints)  // ==> json is [1,2,3,4,5]

        // Deserialization
        val collectionType = object : TypeToken<Collection<Int>>() {}.type
        val ints2 = gson.fromJson<Collection<Int>>(json, collectionType)

        val id = viewModel.generateId()
        val source = send_ticket_input_source.text.toString()
        val destination = send_ticket_input_destination.text.toString()
        val amount = send_ticket_input_amount.text.toString()

        val ticketDetails = listOf(id, source, destination, amount)

        val ticketJson = Gson().toJson(ticketDetails)
        val payload = ticketJson.toByteArray()

        val error = chirpSend.send(payload)
        if (error.code > 0) {
            showErrorMessage()
        } else {
            send_ticket_text_placeholder.text = "Details sent successfully!"
            viewModel.addTicket(
                Ticket(id, source, destination, amount, viewModel.getTimeStamp())
            )
        }
    }

    private fun showErrorMessage() {
        send_ticket_text_placeholder.text = "An error occurred. Please try again"
    }

}
