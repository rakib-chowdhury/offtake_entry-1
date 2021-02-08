package com.groffery.user.di


import com.nuveq.data_entry.di.viewmodel_module.AuthViewModelModule
import com.nuveq.data_entry.di.viewmodel_module.ProductViewModelModule
import com.nuveq.data_entry.di.viewmodel_module.StoreViewModelModule
import com.nuveq.data_entry.feature.auth.RegistrationActivity
import com.nuveq.data_entry.view.activity.DataEntryActivity
import com.nuveq.data_entry.view.activity.HomeActivity
import com.nuveq.data_entry.view.activity.StoreActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [HomeFragmentBindingModule::class])
    abstract fun homeActivity(): HomeActivity


    @ContributesAndroidInjector(modules = [ProductViewModelModule::class])
    abstract fun dataEntryActivity(): DataEntryActivity

    @ContributesAndroidInjector(modules = [AuthViewModelModule::class])
    abstract fun registrationActivity(): RegistrationActivity


    @ContributesAndroidInjector(modules = [StoreViewModelModule::class])
    abstract fun storeActivity(): StoreActivity


}