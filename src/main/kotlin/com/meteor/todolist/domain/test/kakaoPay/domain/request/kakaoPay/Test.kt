package com.meteor.todolist.domain.test.kakaoPay.domain.request.kakaoPay


import com.google.gson.annotations.SerializedName

data class Test(
    @SerializedName("android_app_scheme")
    val androidAppScheme: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("ios_app_scheme")
    val iosAppScheme: String,
    @SerializedName("next_redirect_app_url")
    val nextRedirectAppUrl: String,
    @SerializedName("next_redirect_mobile_url")
    val nextRedirectMobileUrl: String,
    @SerializedName("next_redirect_pc_url")
    val nextRedirectPcUrl: String,
    @SerializedName("tid")
    val tid: String,
    @SerializedName("tms_result")
    val tmsResult: Boolean
)