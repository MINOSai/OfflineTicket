package com.minosai.feature_conductor.ticket.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.minosai.common.extensions.hide
import com.minosai.common.extensions.show
import com.minosai.feature_conductor.ConductorActivity
import com.minosai.feature_conductor.R
import com.minosai.feature_conductor.ticket.ConductorTicketViewModel
import kotlinx.android.synthetic.main.fragment_receive_details.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ReceiveDetailsFragment : Fragment() {

    private val viewModel by sharedViewModel<ConductorTicketViewModel>()

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
            viewModel.generateId()
            findNavController()
                .navigate(R.id.action_receiveDetailsFragment_to_sendTicketFragment)
        }
    }

    private fun receiveDetails() {
        (requireActivity() as ConductorActivity).chirp?.onReceived { payload, _ ->
            activity?.runOnUiThread {
                payload?.let {
                    val identifier = String(it)
                    rec_details_text_placeholder.text = identifier
                    rec_details_button_proceed.show()
                    rec_details_button_retry.hide()
                    viewModel.setPassengerIdFromIdentifier(identifier)
                }
            }
        }
    }
}
