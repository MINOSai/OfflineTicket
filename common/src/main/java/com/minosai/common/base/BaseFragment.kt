package com.minosai.common.base

import android.os.Bundle
import androidx.core.app.NotificationCompat.getExtras
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
//import com.minosai.navigation.NavigationCommand

abstract class BaseFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeNavigation(getViewModel())
    }

    abstract fun getViewModel(): BaseViewModel

    private fun observeNavigation(viewModel: BaseViewModel) {
//        viewModel.navigation.observe(viewLifecycleOwner, Observer {
//            it?.getContentIfNotHandled()?.let { command ->
//                when (command) {
//                    is NavigationCommand.To -> findNavController().navigate(command.directions)
//                    is NavigationCommand.Back -> findNavController().navigateUp()
//                    is NavigationCommand.BackTo -> { }
//                    is NavigationCommand.ToRoot -> { }
//                }
//            }
//        })
    }
}