package com.groffery.user.di


import com.nuveq.data_entry.di.viewmodel_module.AuthViewModelModule
import com.nuveq.data_entry.di.viewmodel_module.ProductViewModelModule
import com.nuveq.data_entry.di.viewmodel_module.StoreViewModelModule
import com.nuveq.data_entry.feature.HistoryFragment
import com.nuveq.data_entry.feature.HomeFragment
import com.nuveq.data_entry.feature.ProfileFragment
import com.nuveq.data_entry.feature.auth.ChangePassFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBindingModule {

    @ContributesAndroidInjector(modules = [StoreViewModelModule::class])
    abstract fun homeFragmentBinding(): HomeFragment


    @ContributesAndroidInjector
    abstract fun profileFragmentBinding(): ProfileFragment


    @ContributesAndroidInjector(modules = [ProductViewModelModule::class])
    abstract fun historyFragmentBinding(): HistoryFragment


    @ContributesAndroidInjector(modules = [AuthViewModelModule::class])
    abstract fun changePassFragmentBinding(): ChangePassFragment


}