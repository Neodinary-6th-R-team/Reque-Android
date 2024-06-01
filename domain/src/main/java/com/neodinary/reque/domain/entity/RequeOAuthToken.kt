package com.neodinary.reque.domain.entity

data class RequeOAuthToken(
    val accessToken : String,
    val refreshToken : String
)