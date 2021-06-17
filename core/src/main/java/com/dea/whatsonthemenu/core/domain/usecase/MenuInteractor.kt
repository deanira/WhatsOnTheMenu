package com.dea.whatsonthemenu.core.domain.usecase

import com.dea.whatsonthemenu.core.domain.repository.MenuRepository
import javax.inject.Inject

class MenuInteractor @Inject constructor(private val menuRepository: MenuRepository): MenuUseCase {
    override fun getMenu(query: String) = menuRepository.getMenu(query)

    override fun getMenuInformation(id: String) = menuRepository.getMenuInformation(id.toInt())
}