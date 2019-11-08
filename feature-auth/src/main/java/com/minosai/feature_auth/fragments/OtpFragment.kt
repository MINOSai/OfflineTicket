package com.minosai.feature_auth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.minosai.feature_auth.AuthActivity
import com.minosai.feature_auth.AuthViewModel
import com.minosai.feature_auth.R
import kotlinx.android.synthetic.main.otp_fragment.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class OtpFragment : Fragment() {

    private val viewModel by sharedViewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.otp_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth_button_otp.setOnClickListener {
            (requireActivity() as AuthActivity).moveToMainActivity()
        }
    }

}
