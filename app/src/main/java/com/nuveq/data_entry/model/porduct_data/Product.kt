package com.nuveq.data_entry.model.porduct_data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.nuveq.data_entry.network.HTTP_PARAM

class Product {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null



    @SerializedName("image")
    @Expose
    var image: String? = null
        get() = HTTP_PARAM.IMAGE_URL + field
    @SerializedName("en_description")
    @Expose
    var enDescription: String? = null

    @SerializedName("bn_description")
    @Expose
    var bnDescription: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("created_by")
    @Expose
    var createdBy: String? = null

    @SerializedName("updated_by")
    @Expose
    var updatedBy: String? = null

    @SerializedName("deleted_at")
    @Expose
    var deletedAt: String? = null

    @SerializedName("skus")
    @Expose
    var skus: List<Sku>? = null
}