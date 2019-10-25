package com.minosai.feature_passenger.balance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.minosai.common.base.BaseFragment
import com.minosai.common.base.BaseViewModel
import com.minosai.common.extensions.getInput

import com.minosai.feature_passenger.R
import kotlinx.android.synthetic.main.balance_fragment.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class BalanceFragment : BaseFragment() {

    private val viewModel by viewModel<BalanceViewModel>()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.balance_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val balance = viewModel.getBalance().toString()
        view.balance_text_current.text = "Current Balance: $balance"

        view.balance_button_pay.setOnClickListener {
            val newBalance = view.balance_input_amount.getInput().toInt()
            viewModel.addBalance(newBalance)
            findNavController().popBackStack()
        }
    }

}
