package com.nuveq.data_entry.di.viewmodel_module

import androidx.lifecycle.ViewModel
import com.nuveq.data_entry.di.ViewModelKey
import com.nuveq.data_entry.feature.ProductViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
@Module
abstract class ProductViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    abstract fun productModelBinding(productViewModel: ProductViewModel): ViewModel
}