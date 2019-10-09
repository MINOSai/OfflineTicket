package com.minosai.feature_auth.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.minosai.common.base.BaseFragment
import com.minosai.common.base.BaseViewModel
import com.minosai.feature_auth.AuthViewModel

import com.minosai.feature_auth.R
import kotlinx.android.synthetic.main.otp_fragment.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class OtpFragment : BaseFragment() {

    private val viewModel by sharedViewModel<AuthViewModel>()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.otp_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth_button_otp.setOnClickListener {
            val otp = auth_input_otp.text.toString()
            viewModel.verifyOtp(otp)
            moveToMainActivity()
        }
    }

    private fun moveToMainActivity() {
        try {
            startActivity(
                Intent(
                    requireActivity(),
                    Class.forName("com.minosai.offlineticket.MainActivity")
                )
            )
            requireActivity().finish()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

}
