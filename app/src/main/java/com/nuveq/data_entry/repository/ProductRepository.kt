package com.nuveq.data_entry.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.nuveq.data_entry.common.App
import com.nuveq.data_entry.common.DataResource
import com.nuveq.data_entry.model.ErrorResponse
import com.nuveq.data_entry.model.data_post.DataEntryPost
import com.nuveq.data_entry.model.history.DataHistoryResponse
import com.nuveq.data_entry.model.history.DatePost
import com.nuveq.data_entry.model.porduct_data.ProductResponse
import com.nuveq.data_entry.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProductRepository @Inject constructor(val apiService: ApiService, val gson: Gson) {
    var postResponse = MutableLiveData<DataResource<ErrorResponse>>()
    var product = MutableLiveData<DataResource<ProductResponse>>()
    var history = MutableLiveData<DataResource<DataHistoryResponse>>()

    fun postProduct(dataEntryPost: DataEntryPost) {
        postResponse.value = DataResource.loading()
        val jsonObject: JsonObject = JsonParser().parse(gson.toJson(dataEntryPost)).getAsJsonObject()
        apiService.postProduct(jsonObject)!!.enqueue(object : Callback<ErrorResponse> {
            override fun onResponse(call: Call<ErrorResponse>, response: Response<ErrorResponse>) {
                if (response.isSuccessful) {
                    postResponse.value = DataResource.success(response.body()!!)
                } else {
                    postResponse.value = DataResource.parseError(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<ErrorResponse>, t: Throwable) {
                postResponse.value = DataResource.error("Something went wrong!")
            }
        })
    }

    fun productData() {
        product.value = DataResource.loading()

        apiService.productList(App.instance.getLang())!!.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if (response.isSuccessful) {
                    if (!response.body()!!.products.isNullOrEmpty()) {
                        product.value = DataResource.success(response.body()!!)
                    } else {
                        product.value = DataResource.error(response.body()!!.message!!)
                    }
                } else {
                    product.value = DataResource.parseError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                product.value = DataResource.error("Something went wrong!")
            }
        })

    }


    fun productHistory(date: String) {
        history.value = DataResource.loading()
        val data = DatePost()
        data.date = date
        val jsonObject: JsonObject = JsonParser().parse(gson.toJson(data)).getAsJsonObject()

        apiService.productHistory(jsonObject)!!.enqueue(object : Callback<DataHistoryResponse> {
            override fun onResponse(call: Call<DataHistoryResponse>, response: Response<DataHistoryResponse>) {
                if (response.isSuccessful) {
                    if (!response.body()!!.history.isNullOrEmpty()) {
                        history.value = DataResource.success(response.body()!!)
                    } else {
                        history.value = DataResource.error(response.body()!!.message!!)
                    }
                } else {
                    history.value = DataResource.parseError(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<DataHistoryResponse>, t: Throwable) {
                history.value = DataResource.error("Something went wrong!")
            }
        })

    }
}