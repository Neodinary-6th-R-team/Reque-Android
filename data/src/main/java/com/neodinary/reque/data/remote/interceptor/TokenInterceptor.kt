package com.neodinary.reque.data.remote.interceptor

import com.neodinary.reque.data.local.datastore.AuthDataStorePreference
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class TokenInterceptor @Inject constructor(
    private val authDataStorePreference: AuthDataStorePreference
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
        // TODO runBlocking이 최선일까?
        runBlocking {
            newRequest.addHeader("Authorization", "Bearer ${authDataStorePreference.getAccessToken()}")
        }
        return chain.proceed(newRequest.build())
    }
}