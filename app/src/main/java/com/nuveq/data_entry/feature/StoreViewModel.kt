package com.nuveq.data_entry.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nuveq.data_entry.common.DataResource
import com.nuveq.data_entry.model.HomeSummery
import com.nuveq.data_entry.model.store.StoreResponse
import com.nuveq.data_entry.repository.StoreRepository
import javax.inject.Inject

class StoreViewModel @Inject constructor(private var repository: StoreRepository) : ViewModel() {

    fun getStoreData() {
        repository.storeData()
    }
   fun getSummeryData() {
        repository.summeryData()
    }



    fun observeStoreList(): LiveData<DataResource<StoreResponse>> {
        return repository.store
    }

    fun observeSummery(): LiveData<DataResource<HomeSummery>> {
        return repository.summery
    }
}