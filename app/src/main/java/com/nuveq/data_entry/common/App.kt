package com.nuveq.data_entry.common

import android.content.Context
import android.content.SharedPreferences
import com.nuveq.data_entry.appdata.AppConstants
import com.nuveq.data_entry.di.DaggerAppComponent
import com.nuveq.data_entry.model.auth.LoginResponse
import com.nuveq.data_entry.model.auth.User
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().bindApplicationInstance(this).build()
    }


    companion object {
        lateinit var instance: App
        lateinit var sharedPref: SharedPreferences

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        sharedPref = getSharedPreferences("app", Context.MODE_PRIVATE)
    }

    fun setLoginPref(login: Boolean, dataResponse: LoginResponse) {
        val edit = sharedPref.edit()
        edit.putBoolean(AppConstants.LOGIN, login)
        edit.putString(AppConstants.TOKEN, dataResponse.token)
        edit.putString(AppConstants.U_NAME, dataResponse.user!!.name)
        edit.putString(AppConstants.U_EMAIL, dataResponse.user!!.email)
        edit.putString(AppConstants.U_PHONE, dataResponse.user!!.phone)
        edit.apply()
    }

    fun getIsLogin(): Boolean {
        return sharedPref.getBoolean(AppConstants.LOGIN, false)
    }


    fun getLoginUser(): User {
        val user = User()
        user.name = sharedPref.getString(AppConstants.U_NAME, "")
        user.email = sharedPref.getString(AppConstants.U_EMAIL, "")
        user.phone = sharedPref.getString(AppConstants.U_PHONE, "")
        return user
    }

    fun getAuthToken(): String {
        return sharedPref.getString(AppConstants.TOKEN, "").toString()
    }
    fun setLangSwitch(isCheck:Boolean){
        val edit = sharedPref.edit()
        if (isCheck){
            edit.putString(AppConstants.LANG, "bn")
        }
        else{
            edit.putString(AppConstants.LANG, "en")
        }
        edit.putBoolean(AppConstants.IS_CHECKED, isCheck)
        edit.apply()

    }


    fun getLang():String{
       return sharedPref.getString(AppConstants.LANG, "").toString()
    }

    fun getLangSwitch():Boolean{
       return sharedPref.getBoolean(AppConstants.IS_CHECKED, false)
    }
}