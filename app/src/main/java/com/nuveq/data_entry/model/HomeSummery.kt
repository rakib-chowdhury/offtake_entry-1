package com.nuveq.data_entry.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeSummery {
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("totalOutLetNumber")
    @Expose
    var totalOutLetNumber: Int? = null

    @SerializedName("target")
    @Expose
    var target: Int? = null

    @SerializedName("targetPerDay")
    @Expose
    var targetPerDay: Int? = null

    @SerializedName("workingDay")
    @Expose
    var workingDay: String? = null


    @SerializedName("raot")
    @Expose
    var raot: Int? = null

    @SerializedName("toDateOffTake")
    @Expose
    var toDateOffTake: String? = null
}