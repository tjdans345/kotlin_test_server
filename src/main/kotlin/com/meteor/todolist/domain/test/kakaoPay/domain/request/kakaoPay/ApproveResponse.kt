package com.meteor.todolist.domain.test.kakaoPay.domain.request.kakaoPay

data class ApproveResponse (
    val aid: String,
    val tid: String,
    val cid: String,
    val sid: String,
    val partnerOrderId: String,
    val paymentMethodType: String,
    val itemName: String,
    val itemCode: String,
    val quantity: Int,
    val createdAt: String,
    val approvedAt: String,
    val payload: String,
    val amount: Amount

        ) {
}