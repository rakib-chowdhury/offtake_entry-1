package com.nuveq.data_entry.model.porduct_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sku {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("remarks")
    @Expose
    var remarks: String? = null
}