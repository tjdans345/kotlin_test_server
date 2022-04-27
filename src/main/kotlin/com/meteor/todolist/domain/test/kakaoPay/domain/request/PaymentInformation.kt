package com.meteor.todolist.domain.test.kakaoPay.domain.request

data class PaymentInformation(
    val itemName: String,
    val totalPrice: String,
    val totalCount: String,
    val productList: MutableList<ProductInformation>
)