package com.nuveq.data_entry.model.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginPost {
    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null
}