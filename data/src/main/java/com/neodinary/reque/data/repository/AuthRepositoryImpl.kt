package com.neodinary.reque.data.repository

import com.neodinary.reque.data.local.datastore.AuthDataStorePreference
import com.neodinary.reque.domain.repository.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authDataStorePreference: AuthDataStorePreference
) : AuthRepository {
    override suspend fun getAccessToken(): String {
        return authDataStorePreference.getAccessToken()
    }

    override suspend fun setAccessToken(accessToken: String) {
        return authDataStorePreference.setAccessToken(accessToken)
    }
}