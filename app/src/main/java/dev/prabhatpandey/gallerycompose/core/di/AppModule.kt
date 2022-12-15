package dev.prabhatpandey.gallerycompose.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.prabhatpandey.gallerycompose.core.data.remote.DataConsts
import dev.prabhatpandey.gallerycompose.core.data.remote.NetworkLogger
import dev.prabhatpandey.gallerycompose.core.data.remote.RemoteApi
import dev.prabhatpandey.retrofitadapter.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRemoteApi(retrofit: Retrofit): RemoteApi = retrofit.create(RemoteApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DataConsts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(120, TimeUnit.SECONDS)
            readTimeout(120, TimeUnit.SECONDS)
            writeTimeout(120, TimeUnit.SECONDS)
            addInterceptor(
                HttpLoggingInterceptor(NetworkLogger()).apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }.build()
    }

}