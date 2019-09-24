package com.minosai.feature_passenger.ticket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.minosai.common.Constants
import com.minosai.common.base.BaseChirpFragment
import com.minosai.common.base.BaseFragment
import com.minosai.common.base.BaseViewModel
import com.minosai.common.show
import com.minosai.common.toast

import com.minosai.feature_passenger.R
import io.chirp.connect.ChirpConnect
import kotlinx.android.synthetic.main.receive_ticket_fragment.*
import kotlinx.android.synthetic.main.send_details_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Exception

class ReceiveTicketFragment : BaseChirpFragment() {

    private val viewModel by viewModel<ReceiveTicketViewModel>()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.receive_ticket_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        receiveTicket()

        rec_ticket_button_retry.setOnClickListener {
            receiveTicket()
        }

    }

    private fun receiveTicket() {
        context?.toast("onRecieved")
        chirpReceive.onReceived { payload, _ ->
            context?.toast(payload.toString())
            activity?.runOnUiThread {
                payload?.let {
                    val identifier = String(it)
                    rec_ticket_text_placeholder.text = identifier
                    rec_ticket_button_retry.show()
                }
            }
        }

        chirpReceive.onReceived { payload, _ ->
            activity?.runOnUiThread {
                payload?.let {
                    context?.toast(String(it))
                }
            }
        }
    }
}
