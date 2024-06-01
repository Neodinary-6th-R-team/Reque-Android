package com.neodinary.reque.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal class AuthDataStorePreference @Inject constructor(
    private val context: Context
) {
    private val Context.datastore by preferencesDataStore(name = "reque")

    private val accessTokenPreference = stringPreferencesKey("ACCESS-TOKEN")

    suspend fun getAccessToken(): String {
        return context.datastore.data.first().let {
            it[accessTokenPreference] ?: ""
        }
    }

    suspend fun setAccessToken(accessToken: String) {
        context.datastore.edit { preference ->
            preference[accessTokenPreference] = accessToken
        }
    }
}