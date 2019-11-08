package com.minosai.feature_auth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.minosai.common.extensions.getInput
import com.minosai.common.extensions.isValidInput
import com.minosai.common.extensions.isValidPhone
import com.minosai.common.extensions.toast
import com.minosai.feature_auth.AuthActivity
import com.minosai.feature_auth.AuthViewModel
import com.minosai.feature_auth.R
import com.minosai.model.Result
import kotlinx.android.synthetic.main.login_fragment.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LoginFragment : Fragment() {

    private val viewModel by sharedViewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth_button_continue.setOnClickListener {
            if (login_input_username.isValidPhone() && login_input_password.isValidInput()) {
                val userName = login_input_username.getInput()
                val password = login_input_password.getInput()

                viewModel.login(userName, password).observe(viewLifecycleOwner, Observer { result ->
                    when (result.status) {
                        Result.Status.LOADING -> toast("Logging in...")
                        Result.Status.SUCCESS -> {
                            toast("User logged in successfully")
                            (requireActivity() as AuthActivity).moveToMainActivity()
                        }
                        Result.Status.ERROR -> toast(result.message!!)
                    }
                })
            }
        }

        login_button_signup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_signupFragment)
        }
    }
}
