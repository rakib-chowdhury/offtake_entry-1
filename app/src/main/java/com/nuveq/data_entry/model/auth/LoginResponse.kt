package com.nuveq.data_entry.model.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("token")
    @Expose
    var token: String? = null

    @SerializedName("user")
    @Expose
    var user: User? = null
}