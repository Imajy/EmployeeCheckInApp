package com.abs.technology.data.di

import android.content.Context
import android.content.SharedPreferences
import com.abs.technology.common.Constants.BASE_URL
import com.abs.technology.common.SharedViewModel
import com.abs.technology.data.remote.AbsApi
import com.abs.technology.data.repository.RepositoryImpl
import com.abs.technology.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideSharePreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("angira_pref", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideApi(): AbsApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AbsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: AbsApi,): Repository {
        return RepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideSessionViewModel(sharedPreferences: SharedPreferences) : SharedViewModel {
        return SharedViewModel(sharedPreferences)
    }

}