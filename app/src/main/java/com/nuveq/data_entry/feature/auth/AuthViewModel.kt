package com.nuveq.data_entry.feature.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nuveq.data_entry.common.DataResource
import com.nuveq.data_entry.model.ErrorResponse
import com.nuveq.data_entry.model.auth.ChangePassPost
import com.nuveq.data_entry.model.auth.LoginPost
import com.nuveq.data_entry.model.auth.LoginResponse
import com.nuveq.data_entry.repository.AuthRepository
import javax.inject.Inject

class AuthViewModel @Inject constructor(var repository: AuthRepository) : ViewModel() {

    fun getUser(): LiveData<DataResource<LoginResponse>> {
        return repository.user
    }


    fun authentication(loginPost: LoginPost) {
        return repository.authentication(loginPost)
    }


  fun changePass(loginPost: ChangePassPost) {
        return repository.changePass(loginPost)
    }


    fun observeChangeUser(): LiveData<DataResource<ErrorResponse>> {
        return repository.changePass
    }
}