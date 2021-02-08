package com.nuveq.data_entry.model.data_post

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataEntryPost {
    @SerializedName("customer_name")
    @Expose
    var customerName: String? = null

    @SerializedName("customer_phone")
    @Expose
    var customerPhone: String? = null

    @SerializedName("living_address")
    @Expose
    var livingAddress: String? = null

    @SerializedName("store_id")
    @Expose
    var storeId: String? = null

    @SerializedName("latitude")
    @Expose
    var latitude: String? = null

    @SerializedName("longitude")
    @Expose
    var longitude: String? = null

    @SerializedName("device")
    @Expose
    var device: String? = null

    @SerializedName("android_version")
    @Expose
    var androidVersion: String? = null

    @SerializedName("products")
    @Expose
    var products: List<Product>? = null
}