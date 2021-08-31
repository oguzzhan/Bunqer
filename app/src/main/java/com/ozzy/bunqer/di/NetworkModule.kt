package com.ozzy.bunqer.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.ozzy.bunqer.data.BunqerService
import com.ozzy.bunqer.util.Constants
import com.ozzy.bunqer.util.extension.signedString
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Oğuzhan Karacan on 30.08.2021.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    fun provideMandatoryHeaderInterceptor(
        bunqPreferences: BunqPreferences
    ): Interceptor {
        return Interceptor { chain ->
            val url = chain.request().url.toString()
            val request = chain.request().newBuilder()
                .addHeader(
                    "Cache-Control", "no-cache"
                )
                .addHeader(
                    "User-Agent",
                    "bunqerOzzy"
                )
                .addHeader(
                    "X-Bunq-Client-Signature",
                    chain.request().body?.signedString() ?: ""
                )
            if (url.contains("installation").not() && url.contains("sandbox-user-person").not()) {
                request.addHeader(
                    "X-Bunq-Client-Authentication",
                    bunqPreferences.getString(Constants.Preferences.SESSION_TOKEN)
                )
            }
            chain.proceed(request.build())
        }
    }

    @Provides
    fun provideChuck(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        mandatoryHeaderInterceptor: Interceptor,
        chuck: ChuckerInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(mandatoryHeaderInterceptor)
            .addInterceptor(chuck)
            .build()

    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.Network.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    fun provideBunqerService(retrofit: Retrofit): BunqerService {
        return retrofit.create(BunqerService::class.java)
    }

}