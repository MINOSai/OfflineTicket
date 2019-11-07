package com.minosai.feature_conductor.ticket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.minosai.common.Constants
import com.minosai.common.base.BaseChirpFragment
import com.minosai.common.base.BaseViewModel
import com.minosai.feature_conductor.ticket.ConductorTicketViewModel
import io.chirp.chirpsdk.ChirpSDK
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

        val source = send_ticket_input_source.text.toString()
        val destination = send_ticket_input_destination.text.toString()
        val amount = send_ticket_input_amount.text.toString()

        val ticket = viewModel.generateTicket(source, destination, amount)

        val error = chirp?.send(viewModel.getPayload(ticket))
        if (error?.code!! > 0) {
            showErrorMessage()
        } else {
            send_ticket_text_placeholder.text = "Details sent successfully!"
            viewModel.addTicket(ticket)
        }
    }

    private fun showErrorMessage() {
        send_ticket_text_placeholder.text = "An error occurred. Please try again"
    }

    override fun getFragmentName() = "conductor send ticket"

    override fun initializeChirp() {
        chirp = ChirpSDK(
            requireContext(),
            Constants.CHIRP_APP_KEY,
            Constants.CHIRP_APP_SECRET
        ).apply { setConfig(Constants.CHIRP_APP_CONFIG) }
    }

}
