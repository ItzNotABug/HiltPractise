package com.lazygeniouz.hiltpractise.module

import com.lazygeniouz.hiltpractise.data.api.CoinGeckoApi
import com.lazygeniouz.hiltpractise.extensions.getBaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/**
 * DI Module to provide relevant objects when required using @[Inject]
 */
@Module
@InstallIn(SingletonComponent::class)
object DIModule {

    /**
     * Injects a [Retrofit] object wherever requested
     */
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    /**
     * Injects a [CoinGeckoApi] object wherever requested
     */
    @Provides
    fun provideApiInterface(retrofit: Retrofit): CoinGeckoApi =
        retrofit.create(CoinGeckoApi::class.java)
}