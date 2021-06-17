package com.dea.whatsonthemenu.ui.pasta

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dea.whatsonthemenu.core.data.Resource
import com.dea.whatsonthemenu.core.domain.model.Menu
import com.dea.whatsonthemenu.core.domain.usecase.MenuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PastaViewModel @Inject constructor(
    private val menuUseCase: MenuUseCase
) : ViewModel() {
    fun getPastas(): LiveData<Resource<List<Menu>>> =
        menuUseCase.getMenu("Pasta").asLiveData()
}