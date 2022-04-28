package com.meteor.todolist.domain.test.kakaoPay.domain.request.kakaoPay


import com.google.gson.annotations.SerializedName

data class AmountX(
    @SerializedName("discount")
    val discount: Int,
    @SerializedName("point")
    val point: Int,
    @SerializedName("tax_free")
    val taxFree: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("vat")
    val vat: Int
)