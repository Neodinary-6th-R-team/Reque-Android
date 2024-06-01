package com.neodinary.reque.data.di

import com.neodinary.reque.data.remote.api.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
    @Provides
    fun provideAuthApi(
        retrofit: Retrofit
    ) = retrofit.create(AuthApi::class.java)
}