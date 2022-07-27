package com.meteor.todolist.domain.test.kakaoPay.domain.request

data class CompleteInformation(
    val tid: String,
    val pgToken: String,
    val partnerOrderId: String,
    val partnerUserId: String
) {

}
