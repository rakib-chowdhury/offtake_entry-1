package com.nuveq.data_entry.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.nuveq.data_entry.common.DataResource
import com.nuveq.data_entry.model.ErrorResponse
import com.nuveq.data_entry.model.auth.ChangePassPost
import com.nuveq.data_entry.model.auth.LoginPost
import com.nuveq.data_entry.model.auth.LoginResponse
import com.nuveq.data_entry.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class AuthRepository @Inject constructor(val apiService: ApiService, val gson: Gson) {

    var user = MutableLiveData<DataResource<LoginResponse>>()
    var changePass = MutableLiveData<DataResource<ErrorResponse>>()

    fun authentication(loginPost: LoginPost) {
        user.value = DataResource.loading()
        val jsonObject: JsonObject = JsonParser().parse(gson.toJson(loginPost)).getAsJsonObject()

        apiService.login(jsonObject!!)!!.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    user.value = DataResource.success(response.body()!!)
                } else {
                    user.value = DataResource.parseError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                user.value = DataResource.error("Something went wrong!")
            }
        })

    }


    fun changePass(loginPost: ChangePassPost) {
        changePass.value = DataResource.loading()
        val jsonObject: JsonObject = JsonParser().parse(gson.toJson(loginPost)).getAsJsonObject()

        apiService.changePass(jsonObject!!)!!.enqueue(object : Callback<ErrorResponse> {
            override fun onResponse(call: Call<ErrorResponse>, response: Response<ErrorResponse>) {
                if (response.isSuccessful) {
                    changePass.value = DataResource.success(response.body()!!)
                } else {
                    changePass.value = DataResource.parseError(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<ErrorResponse>, t: Throwable) {
                changePass.value = DataResource.error("Something went wrong!")
            }
        })

    }
}