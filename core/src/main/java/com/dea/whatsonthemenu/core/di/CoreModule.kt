package com.dea.whatsonthemenu.core.di

import android.content.Context
import androidx.room.Room
import com.dea.whatsonthemenu.core.BuildConfig
import com.dea.whatsonthemenu.core.data.source.local.LocalDataSource
import com.dea.whatsonthemenu.core.data.source.local.room.MenuDao
import com.dea.whatsonthemenu.core.data.source.local.room.MenuDatabase
import com.dea.whatsonthemenu.core.data.source.remote.RemoteDataSource
import com.dea.whatsonthemenu.core.data.source.remote.network.ApiService
import com.dea.whatsonthemenu.core.utils.AppExecutors
import com.dea.whatsonthemenu.core.utils.Constant
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.internal.platform.android.ConscryptSocketAdapter.Companion.factory
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    fun provideBaseURl() = Constant.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = run {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        MenuDatabase::class.java,
        "favourite_db.db"
    ).build()

    @Singleton
    @Provides
    fun provideFavouriteMenuDao(db: MenuDatabase) = db.menuDao()

    @Singleton
    @Provides
    fun provideMenuService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideLocalDataSource(dao: MenuDao) = LocalDataSource(dao)

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService) = RemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideAppExecutor() = AppExecutors()
}