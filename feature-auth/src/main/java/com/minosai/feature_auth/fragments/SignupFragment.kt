package com.minosai.feature_auth.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.minosai.common.base.BaseFragment
import com.minosai.common.base.BaseViewModel
import com.minosai.common.extensions.getInput
import com.minosai.common.extensions.isValidInput
import com.minosai.common.extensions.isValidPhone
import com.minosai.common.extensions.toast
import com.minosai.feature_auth.AuthActivity
import com.minosai.feature_auth.AuthViewModel
import com.minosai.feature_auth.R
import com.minosai.model.Result
import kotlinx.android.synthetic.main.fragment_signup.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SignupFragment : BaseFragment() {

    private val viewModel by sharedViewModel<AuthViewModel>()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_signup.setOnClickListener {
            if (signup_input_username.isValidPhone() && signup_input_password.isValidInput()) {
                val userName = signup_input_username.getInput()
                val password = signup_input_password.getInput()

                viewModel.signup(userName, password).observe(this, Observer { result ->
                    when (result.status) {
                        Result.Status.LOADING -> toast("Signing up...")
                        Result.Status.SUCCESS -> login(userName, password)
                        Result.Status.ERROR -> toast(result.message!!)
                    }
                })
            }
        }

        signup_button_login.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun login(userName: String, password: String) {
        viewModel.login(userName, password).observe(this, Observer { result ->
            when (result.status) {
                Result.Status.LOADING -> {
                }
                Result.Status.SUCCESS -> {
                    toast("User created successfully")
                    (requireActivity() as AuthActivity).moveToMainActivity()
                }
                Result.Status.ERROR -> toast(result.message!!)
            }
        })
    }
}
