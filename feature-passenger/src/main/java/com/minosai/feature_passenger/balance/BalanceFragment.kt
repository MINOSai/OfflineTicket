package com.minosai.feature_passenger.balance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.minosai.common.base.BaseFragment
import com.minosai.common.base.BaseViewModel
import com.minosai.common.extensions.getInput
import com.minosai.common.extensions.toast

import com.minosai.feature_passenger.R
import com.minosai.model.Result
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
            val amount = view.balance_input_amount.getInput().toInt()
            viewModel.addBalanceToServer(amount).observe(this, Observer { result ->
                when (result.status) {
                    Result.Status.LOADING -> toast("Updating balance...")
                    Result.Status.SUCCESS -> {
                        toast("Balance updated successfully")
                        findNavController().popBackStack()
                    }
                    Result.Status.ERROR -> toast(result.message!!)
                }

            })
        }
    }

}
