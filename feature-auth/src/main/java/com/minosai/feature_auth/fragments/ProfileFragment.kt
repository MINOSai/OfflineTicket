package com.minosai.feature_auth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.minosai.common.Constants
import com.minosai.feature_auth.AuthViewModel
import com.minosai.feature_auth.R
import kotlinx.android.synthetic.main.profile_fragment.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ProfileFragment : Fragment() {

    private val viewModel by sharedViewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth_button_passenger.setOnClickListener {
            viewModel.profileType = Constants.PROFILE_PASSENGER
            moveToLoginScreen()
        }

        auth_button_conductor.setOnClickListener {
            viewModel.profileType = Constants.PROFILE_CONDUCTOR
            moveToLoginScreen()
        }
    }

    private fun moveToLoginScreen() =
        findNavController()
            .navigate(R.id.action_profileFragment_to_loginFragment2)

}
