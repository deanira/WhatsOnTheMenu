package com.dea.whatsonthemenu.menufavorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dea.whatsonthemenu.core.domain.usecase.MenuUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val useCase: MenuUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass != FavoriteViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return FavoriteViewModel(
            useCase
        ) as T
    }
}