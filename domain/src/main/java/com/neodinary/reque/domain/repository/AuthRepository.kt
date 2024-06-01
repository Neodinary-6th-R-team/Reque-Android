package com.neodinary.reque.domain.repository

interface AuthRepository {
    suspend fun getAccessToken() : String
    suspend fun setAccessToken(accessToken : String)
}