package com.nuveq.data_entry.model.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChangePassPost {
    @SerializedName("password")
    @Expose
    var password: String? = null

    @SerializedName("new_password")
    @Expose
    var newPassword: String? = null

    @SerializedName("confirm_password")
    @Expose
    var confirmPassword: String? = null
}