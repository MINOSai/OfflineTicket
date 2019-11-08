package com.minosai.feature_auth.di

import com.minosai.feature_auth.AuthViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val featureAuthModule = module {
    viewModel { AuthViewModel(get()) }
}