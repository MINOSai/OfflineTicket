package com.minosai.feature_passenger.ticket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.minosai.common.base.BaseChirpFragment
import com.minosai.common.base.BaseFragment
import com.minosai.common.base.BaseViewModel
import com.minosai.common.extensions.show
import com.minosai.feature_passenger.PassengerActivity
import com.minosai.feature_passenger.R
import kotlinx.android.synthetic.main.receive_ticket_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ReceiveTicketFragment : BaseFragment() {

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

        receive_ticket_ripple.startRippleAnimation()

        rec_ticket_button_retry.setOnClickListener {
            receiveTicket()
        }

    }

    private fun receiveTicket() {
        (requireActivity() as PassengerActivity).chirp?.onReceived { payload, _ ->
            activity?.runOnUiThread {
                payload?.let {
                    val identifier = String(it)
                    viewModel.addTicketUsingIdentifier(identifier)

                    rec_ticket_text_placeholder.text = identifier
                    rec_ticket_button_retry.show()
                }
            }
        }
    }
}
