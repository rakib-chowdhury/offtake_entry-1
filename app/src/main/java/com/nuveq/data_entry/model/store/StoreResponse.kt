package com.nuveq.data_entry.model.store

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StoreResponse {
    @SerializedName("message")
    @Expose
     var message: String? = null

    @SerializedName("code")
    @Expose
     var code: String? = null



    @SerializedName("stores")
    @Expose
    var stores: List<Store>? = null
}