package com.neodinary.reque.data.di

import android.content.Context
import com.neodinary.reque.data.local.datastore.AuthDataStorePreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LocalModule {
    @Provides
    @Singleton
    fun provideAuthDataStorePreference(
        @ApplicationContext context : Context
    ) : AuthDataStorePreference {
        return AuthDataStorePreference(context)
    }
}