package com.minosai.feature_auth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.minosai.common.base.BaseFragment
import com.minosai.common.base.BaseViewModel
import com.minosai.feature_auth.AuthViewModel
import com.minosai.feature_auth.R
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LoginFragment : BaseFragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel by sharedViewModel<AuthViewModel>()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth_button_continue.setOnClickListener {
            val phNo = auth_input_phno.text.toString()
            viewModel.phoneNumber = phNo
            findNavController().navigate(R.id.action_loginFragment2_to_otpFragment)
        }
    }
}
