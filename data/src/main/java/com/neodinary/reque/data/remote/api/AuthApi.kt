package com.neodinary.reque.data.remote.api

import com.neodinary.reque.data.remote.dto.ServerResponse
import retrofit2.http.GET

internal interface AuthApi {
    @GET("auth/login")
    suspend fun doLogin(

    ) : ServerResponse<Unit>
}