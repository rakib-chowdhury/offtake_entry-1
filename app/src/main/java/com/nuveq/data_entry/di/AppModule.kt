package com.nuveq.data_entry.di

import com.google.gson.Gson
import com.nuveq.data_entry.common.App
import com.nuveq.data_entry.network.ApiService
import com.nuveq.data_entry.network.HTTP_PARAM.BASE_URL


import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class AppModule {


    @Singleton
    @Provides
    open fun provideRetrofitInstance(): Retrofit {

        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val request: okhttp3.Request
                    if (App.instance.getAuthToken() == "") {
                        request = chain.request().newBuilder().addHeader("Authorization", "").build()
                    } else {
                        request = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer ${App.instance.getAuthToken()}").build()
                    }
                    chain.proceed(request)
                }
                .build()
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }


}