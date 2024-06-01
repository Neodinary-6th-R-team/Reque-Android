package com.neodinary.reque.data.remote.dto

internal data class ServerResponse<T>(
    val code: Int,
    val message: String,
    val data: T
)