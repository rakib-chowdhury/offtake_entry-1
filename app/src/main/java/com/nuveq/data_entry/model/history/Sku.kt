package com.nuveq.data_entry.model.history

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sku {
    @SerializedName("sku_id")
    @Expose
    var skuId: String? = null

    @SerializedName("sku_name")
    @Expose
    var skuName: String? = null

    @SerializedName("product_name")
    @Expose
    var productName: String? = null

    @SerializedName("quantity")
    @Expose
    var quantity: String? = null
}