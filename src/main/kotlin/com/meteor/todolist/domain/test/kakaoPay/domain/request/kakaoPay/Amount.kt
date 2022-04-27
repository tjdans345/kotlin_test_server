package com.meteor.todolist.domain.test.kakaoPay.domain.request.kakaoPay

import com.fasterxml.jackson.annotation.JsonProperty

data class Amount (
    val total: Int,
    @JsonProperty("tax_free")
    val taxFree: Int,
    val vat: Int,
    val point: Int,
    val discount: Int
        ) {
}