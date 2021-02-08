package com.nuveq.data_entry.model.store

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Store {
    @SerializedName("store_id")
    @Expose
    var storeId: Int? = null

    @SerializedName("store_name")
    @Expose
    var storeName: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

    @SerializedName("contact_person_name")
    @Expose
    var contactPersonName: String? = null

    @SerializedName("contact_person_number")
    @Expose
    var contactPersonNumber: String? = null
}