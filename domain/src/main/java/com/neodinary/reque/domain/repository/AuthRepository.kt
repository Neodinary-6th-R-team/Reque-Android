package com.neodinary.reque.domain.repository

interface AuthRepository {
    suspend fun getAccessToken() : String
}