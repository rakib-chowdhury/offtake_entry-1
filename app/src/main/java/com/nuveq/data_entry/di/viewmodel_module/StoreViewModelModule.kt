package com.nuveq.data_entry.di.viewmodel_module

import androidx.lifecycle.ViewModel
import com.nuveq.data_entry.di.ViewModelKey
import com.nuveq.data_entry.feature.StoreViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class StoreViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(StoreViewModel::class)
    abstract fun storeModelBinding(storeViewModel: StoreViewModel): ViewModel
}