package com.minosai.feature_conductor.ticket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.minosai.common.base.BaseChirpFragment
import com.minosai.common.base.BaseViewModel
import com.minosai.common.extensions.hide
import com.minosai.common.extensions.show
import com.minosai.feature_conductor.R
import com.minosai.feature_conductor.ticket.ConductorTicketViewModel
import kotlinx.android.synthetic.main.fragment_receive_details.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ReceiveDetailsFragment : BaseChirpFragment() {

    private val viewModel by sharedViewModel<ConductorTicketViewModel>()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_receive_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        receiveDetails()

        receive_details_ripple.startRippleAnimation()

        rec_details_button_retry.setOnClickListener {
            receiveDetails()
        }

        rec_details_button_proceed.setOnClickListener {
            findNavController()
                .navigate(R.id.action_receiveDetailsFragment_to_sendTicketFragment)
        }
    }

    private fun receiveDetails() {
        chirpReceive.onReceived { payload, _ ->
            activity?.runOnUiThread {
                payload?.let {
                    val identifier = String(it)
                    rec_details_text_placeholder.text = identifier
                    rec_details_button_proceed.show()
                    rec_details_button_retry.hide()
                }
            }
        }
    }
}
