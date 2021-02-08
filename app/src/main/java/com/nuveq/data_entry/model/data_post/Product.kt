package com.nuveq.data_entry.model.data_post

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Product {
    @SerializedName("sku_id")
    @Expose
    var skuId: Int? = null

    @SerializedName("quantity")
    @Expose
    var quantity: Int? = null

    var skuName: String? = null
    var productName: String? = null
}