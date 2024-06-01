package com.neodinary.reque.data.di

import com.neodinary.reque.data.local.datastore.AuthDataStorePreference
import com.neodinary.reque.data.remote.interceptor.NetworkInterceptor
import com.neodinary.reque.data.remote.interceptor.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private const val SERVER_BASE_URL = "https://port-0-reque-server-ss7z32llwvcl9qj.sel5.cloudtype.app/api/v1/"

    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("okHttpClient") okHttpClient: OkHttpClient
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(SERVER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("okHttpClient")
    fun provideOkHttpInterceptor(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @Named("networkInterceptor") networkInterceptor: NetworkInterceptor,
        @Named("tokenInterceptor") tokenInterceptor: TokenInterceptor
    ) : OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addNetworkInterceptor(tokenInterceptor)
            .addInterceptor(networkInterceptor)

        if(BuildConfig.DEBUG){
            builder.addInterceptor(httpLoggingInterceptor)
        }

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    @Named("networkInterceptor")
    fun provideNetworkInterceptor(
        authDataStorePreference: AuthDataStorePreference
    ) : NetworkInterceptor {
        return NetworkInterceptor(authDataStorePreference)
    }

    @Singleton
    @Provides
    @Named("tokenInterceptor")
    fun provideTokenInterceptor(
        authDataStorePreference: AuthDataStorePreference
    ) : TokenInterceptor {
        return TokenInterceptor(authDataStorePreference)
    }
}