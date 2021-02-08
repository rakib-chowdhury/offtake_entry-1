package com.nuveq.data_entry.model.history

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class History {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("customer_name")
    @Expose
    var customerName: String? = null

    @SerializedName("customer_contact_number")
    @Expose
    var customerContactNumber: String? = null

    @SerializedName("living_area")
    @Expose
    var livingArea: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("skus")
    @Expose
    var skus: List<Sku>? = null
}