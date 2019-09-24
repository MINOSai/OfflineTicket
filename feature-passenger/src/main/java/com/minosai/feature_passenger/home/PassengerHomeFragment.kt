package com.minosai.feature_passenger.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.minosai.common.base.BaseFragment
import com.minosai.common.base.BaseViewModel
import com.minosai.feature_passenger.R
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
    }
}
