package com.nuveq.data_entry.model.porduct_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductResponse {
    @SerializedName("message")
    @Expose
     var message: String? = null

    @SerializedName("code")
    @Expose
     var code: String? = null


    @SerializedName("products")
    @Expose
    val products: List<Product>? = null
}