package com.nuveq.data_entry.network

import com.nuveq.data_entry.common.App
import com.nuveq.data_entry.network.HTTP_PARAM.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RestAPiClient {


    val apiService: ApiService
        get()  {

            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor {chain->
                    val request : okhttp3.Request
                    if (App.instance.getAuthToken()==""){
                        request = chain.request().newBuilder().addHeader("Authorization", "").build()
                    }else{
                        request = chain.request().newBuilder().addHeader("Authorization", "Token ${App.instance.getAuthToken()}").build()
                    }
                    chain.proceed(request)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }


}