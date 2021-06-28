package com.dea.whatsonthemenu.ui.detail

import androidx.lifecycle.*
import com.dea.whatsonthemenu.core.data.Resource
import com.dea.whatsonthemenu.core.data.source.local.entity.MenuEntity
import com.dea.whatsonthemenu.core.domain.model.Menu
import com.dea.whatsonthemenu.core.domain.model.MenuInformation
import com.dea.whatsonthemenu.core.domain.usecase.MenuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val menuUseCase: MenuUseCase
) : ViewModel() {
    val menu = MutableLiveData<MenuEntity>()
    val menuInfo = MutableLiveData<MenuInformation>()

    fun setFavoriteMenu(menu: Menu, newStatus: Boolean) {
        menuUseCase.setFavoriteMenu(menu, newStatus)
    }

    fun getMenu(idDb: Int) = viewModelScope.launch {
        val data = menuUseCase.getFavoriteMenu(idDb)
//        data.collect {
//            menu.postValue(it)
//        }
    }

    fun getMenuInformation(idDb: String): LiveData<Resource<MenuInformation>> =
        menuUseCase.getMenuInformation(idDb).asLiveData()

}