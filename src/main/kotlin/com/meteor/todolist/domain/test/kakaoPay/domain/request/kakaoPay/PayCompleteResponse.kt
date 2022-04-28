package com.meteor.todolist.domain.test.kakaoPay.domain.request.kakaoPay


import com.google.gson.annotations.SerializedName

data class PayCompleteResponse(
    @SerializedName("aid")
    val aid: String,
    @SerializedName("amount")
    val amount: AmountX,
    @SerializedName("approved_at")
    val approvedAt: String,
    @SerializedName("cid")
    val cid: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("item_name")
    val itemName: String,
    @SerializedName("partner_order_id")
    val partnerOrderId: String,
    @SerializedName("partner_user_id")
    val partnerUserId: String,
    @SerializedName("payment_method_type")
    val paymentMethodType: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("tid")
    val tid: String
)