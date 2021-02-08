package com.nuveq.data_entry.model.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("user_id")
    @Expose
    var userId: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null

    @SerializedName("last_login")
    @Expose
    var lastLogin: Any? = null

    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("is_super_user")
    @Expose
    var isSuperUser: Int? = null

    @SerializedName("has_app_access")
    @Expose
    var hasAppAccess: Int? = null

    @SerializedName("has_admin_panel_access")
    @Expose
    var hasAdminPanelAccess: Int? = null

    @SerializedName("role_id")
    @Expose
    var roleId: Any? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("deleted_at")
    @Expose
    var deletedAt: Any? = null
}