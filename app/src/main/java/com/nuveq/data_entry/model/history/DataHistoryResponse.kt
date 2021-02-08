package com.nuveq.data_entry.model.history

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataHistoryResponse {
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("history")
    @Expose
    var history: List<History>? = null
}