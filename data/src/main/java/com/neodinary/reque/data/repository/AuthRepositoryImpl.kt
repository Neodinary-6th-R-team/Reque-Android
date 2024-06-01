package com.neodinary.reque.data.repository

import com.neodinary.reque.data.local.datastore.AuthDataStorePreference
import com.neodinary.reque.domain.repository.AuthRepository

class AuthRepositoryImpl constructor(
    private val authDataStorePreference: AuthDataStorePreference
) : AuthRepository {
    override suspend fun getAccessToken(): String {
        return authDataStorePreference.getAccessToken()
    }
}