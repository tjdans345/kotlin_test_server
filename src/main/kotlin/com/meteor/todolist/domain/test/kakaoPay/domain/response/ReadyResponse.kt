package com.meteor.todolist.domain.test.kakaoPay.domain.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ReadyResponse (
    val tid: String,
    @JsonProperty("next_redirect_pc_url")
    val nextRedirectPcUrl: String,
    @JsonProperty("partner_order_id")
    val partnerOrderId: String
        ) {
}