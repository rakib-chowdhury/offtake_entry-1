package com.nuveq.data_entry.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.nuveq.data_entry.common.App

import com.nuveq.data_entry.common.DataResource
import com.nuveq.data_entry.model.HomeSummery

import com.nuveq.data_entry.model.store.StoreResponse
import com.nuveq.data_entry.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class StoreRepository @Inject constructor(val apiService: ApiService, val gson: Gson) {

    var store = MutableLiveData<DataResource<StoreResponse>>()
    var summery = MutableLiveData<DataResource<HomeSummery>>()

    fun storeData() {
        store.value = DataResource.loading()

        apiService.storeList(App.instance.getLang())!!.enqueue(object : Callback<StoreResponse> {
            override fun onResponse(call: Call<StoreResponse>, response: Response<StoreResponse>) {

                if (response.isSuccessful) {
                    if (!response.body()!!.stores.isNullOrEmpty()) {
                        store.value = DataResource.success(response.body()!!)
                    } else {
                        store.value = DataResource.error(response.body()!!.message!!)
                    }
                } else {
                    store.value = DataResource.parseError(response.errorBody().toString())

                }

            }

            override fun onFailure(call: Call<StoreResponse>, t: Throwable) {
                store.value = DataResource.error("Something went wrong!")
            }
        })

    }


    fun summeryData() {
        summery.value = DataResource.loading()

        apiService.homeSummery(App.instance.getLang())!!.enqueue(object : Callback<HomeSummery> {
            override fun onResponse(call: Call<HomeSummery>, response: Response<HomeSummery>) {

                if (response.isSuccessful) {
                    if (response.body()!!.workingDay != null) {
                        summery.value = DataResource.success(response.body()!!)
                    } else {
                        summery.value = DataResource.error(response.body()!!.message!!)
                    }
                } else {
                    summery.value = DataResource.error(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<HomeSummery>, t: Throwable) {
                summery.value = DataResource.error("Something went wrong!")
            }
        })
    }

}