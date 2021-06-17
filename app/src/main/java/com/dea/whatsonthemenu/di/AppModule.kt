package com.dea.whatsonthemenu.di

import com.dea.whatsonthemenu.core.data.MenuRepositoryImpl
import com.dea.whatsonthemenu.core.domain.repository.MenuRepository
import com.dea.whatsonthemenu.core.domain.usecase.MenuInteractor
import com.dea.whatsonthemenu.core.domain.usecase.MenuUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideMenuUseCase(menuRepository: MenuRepository) = MenuInteractor(menuRepository) as MenuUseCase
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    abstract fun bindMenuRepository(menuRepositoryImpl: MenuRepositoryImpl): MenuRepository
}
