package com.nuveq.data_entry.di

import android.app.Application
import com.groffery.user.di.ActivityBindingModule
import com.nuveq.data_entry.common.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBindingModule::class])
open interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindApplicationInstance(application: Application): Builder

        fun build(): AppComponent
    }
}