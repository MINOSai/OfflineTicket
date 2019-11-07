package com.minosai.feature_passenger.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.minosai.common.Constants
import com.minosai.common.base.BaseChirpFragment
import com.minosai.common.base.BaseViewModel
import com.minosai.common.extensions.show
import com.minosai.feature_passenger.R
import io.chirp.chirpsdk.ChirpSDK
import kotlinx.android.synthetic.main.send_details_fragment.*
import kotlinx.android.synthetic.main.send_details_fragment.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class SendDetailsFragment : BaseChirpFragment() {

    private val viewModel by viewModel<SendDetailsViewModel>()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.send_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        send_details_ripple.startRippleAnimation()

//        sendDetails()

        view.send_details_button_resend.setOnClickListener {
            sendDetails()
        }

        view.send_details_button_receive_ticket.setOnClickListener {
            findNavController().navigate(R.id.action_sendDetailsFragment_to_receiveTicketFragment)
        }
    }

    private fun sendDetails() {
        val error = chirp?.send(viewModel.getPayload())
        if (error?.code!! > 0) {
            showErrorMessage()
        } else {
            send_details_text_placeholder.text = "Details sent successfully!"
            send_details_button_resend.show()
            send_details_button_receive_ticket.show()
        }
    }

    private fun showErrorMessage() {
        send_details_text_placeholder.text = "An error occurred. Please try again"
        send_details_button_resend.show()
    }

    override fun getFragmentName() = "passenger send details"

    override fun initializeChirp() {
        chirp = ChirpSDK(
            requireContext(),
            Constants.CHIRP_APP_KEY,
            Constants.CHIRP_APP_SECRET
        ).apply { setConfig(Constants.CHIRP_APP_CONFIG) }
    }
}
