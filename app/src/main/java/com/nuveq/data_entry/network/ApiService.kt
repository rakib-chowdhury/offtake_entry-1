package com.nuveq.data_entry.network

import com.google.gson.JsonObject
import com.nuveq.data_entry.model.ErrorResponse
import com.nuveq.data_entry.model.HomeSummery
import com.nuveq.data_entry.model.auth.LoginResponse
import com.nuveq.data_entry.model.history.DataHistoryResponse
import com.nuveq.data_entry.model.porduct_data.ProductResponse
import com.nuveq.data_entry.model.store.StoreResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Contains all API call declarations
 */
interface ApiService {


    @GET(HTTP_PARAM.STORE_LIST)
    fun storeList(): Call<StoreResponse>

    @POST(HTTP_PARAM.SUBMIT_DATA)
    fun postProduct(@Body data: JsonObject): Call<ErrorResponse>

    @POST(HTTP_PARAM.LOGIN)
    fun login(@Body data: JsonObject): Call<LoginResponse>


    @POST(HTTP_PARAM.CHANGE_PASS)
    fun changePass(@Body data: JsonObject): Call<ErrorResponse>


    @GET(HTTP_PARAM.PRODUCT_LIST)
    fun productList(@Header("lang") lang:String): Call<ProductResponse>


    @POST(HTTP_PARAM.HISTORY_LIST)
    fun productHistory(@Body data: JsonObject): Call<DataHistoryResponse>


    @GET(HTTP_PARAM.HOME_SUMMERY)
    fun homeSummery(): Call<HomeSummery>
}