package com.nuveq.data_entry.di.viewmodel_module

import androidx.lifecycle.ViewModel

import com.nuveq.data_entry.di.ViewModelKey
import com.nuveq.data_entry.feature.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun authModelBinding(authViewModel: AuthViewModel): ViewModel
}