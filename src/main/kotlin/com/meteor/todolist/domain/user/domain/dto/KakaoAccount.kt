package com.meteor.todolist.domain.user.domain.dto
import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoAccount(
    @JsonProperty("age_range")
    val ageRange: String,
    @JsonProperty("age_range_needs_agreement")
    val ageRangeNeedsAgreement: Boolean,
    @JsonProperty("birthday")
    val birthday: String,
    @JsonProperty("birthday_needs_agreement")
    val birthdayNeedsAgreement: Boolean,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("email_needs_agreement")
    val emailNeedsAgreement: Boolean,
    @JsonProperty("gender")
    val gender: String,
    @JsonProperty("gender_needs_agreement")
    val genderNeedsAgreement: Boolean,
    @JsonProperty("is_email_valid")
    val isEmailValid: Boolean,
    @JsonProperty("is_email_verified")
    val isEmailVerified: Boolean,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("name_needs_agreement")
    val nameNeedsAgreement: Boolean,
    @JsonProperty("profile")
    val profile: Profile,
    @JsonProperty("profile_needs_agreement")
    val profileNeedsAgreement: Boolean
)