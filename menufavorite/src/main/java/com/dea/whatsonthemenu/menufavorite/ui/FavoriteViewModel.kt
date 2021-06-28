package com.dea.whatsonthemenu.menufavorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dea.whatsonthemenu.core.domain.model.Menu
import com.dea.whatsonthemenu.core.domain.usecase.MenuUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val menuUseCase: MenuUseCase
): ViewModel() {
    fun getFavoriteMenus(): LiveData<List<Menu>> = menuUseCase.getFavoriteMenus().asLiveData()
}