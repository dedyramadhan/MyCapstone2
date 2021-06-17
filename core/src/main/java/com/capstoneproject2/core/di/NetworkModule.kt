package com.capstoneproject2.core.di

import com.capstoneproject2.core.BuildConfig
import com.capstoneproject2.core.data.source.remote.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {
    @Provides
    fun provideApiService(client: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val hostname = BuildConfig.host_name
        val certificate = CertificatePinner.Builder()
                .add(hostname, BuildConfig.pin_certificate_1)
                .add(hostname, BuildConfig.pin_certificate_2)
                .add(hostname, BuildConfig.pin_certificate_3)
                .build()
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .certificatePinner(certificate)
                .build()
    }

}