package com.dea.whatsonthemenu.menufavorite.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dea.whatsonthemenu.menufavorite.ui.FavoriteViewModel
import com.dea.whatsonthemenu.menufavorite.ui.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteModule {
    @Singleton
    @Provides
    fun providePostDetailViewModel(activity: AppCompatActivity, factory: ViewModelFactory) =
        ViewModelProvider(activity, factory).get(FavoriteViewModel::class.java)
}