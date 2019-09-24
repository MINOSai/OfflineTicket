package com.minosai.feature_conductor.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.minosai.common.base.BaseFragment
import com.minosai.common.base.BaseViewModel

import com.minosai.feature_conductor.R
import kotlinx.android.synthetic.main.conductor_home_fragment.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class ConductorHomeFragment : BaseFragment() {
    
    override fun getViewModel(): BaseViewModel = viewModel

    private val viewModel by viewModel<ConductorHomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.conductor_home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.home_button_new_ticket.setOnClickListener {
            findNavController()
                .navigate(R.id.action_conductorHomeFragment_to_receiveDetailsFragment)
        }
    }

}
