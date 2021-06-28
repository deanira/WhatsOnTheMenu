package com.dea.whatsonthemenu.di

import com.dea.whatsonthemenu.core.domain.usecase.MenuUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DynamicFeatureDependencies {
    fun sampleRepository(): MenuUseCase
}