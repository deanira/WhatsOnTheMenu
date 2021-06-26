package com.dea.whatsonthemenu.core.domain.usecase

import com.dea.whatsonthemenu.core.data.source.local.entity.MenuEntity
import com.dea.whatsonthemenu.core.domain.model.Menu
import com.dea.whatsonthemenu.core.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MenuInteractor @Inject constructor(private val menuRepository: MenuRepository): MenuUseCase {
    override fun getMenu(query: String) = menuRepository.getMenu(query)
    override fun getMenuInformation(id: String) = menuRepository.getMenuInformation(id.toInt())
    override fun setFavoriteMenu(menu: Menu, state: Boolean) = menuRepository.setFavoriteMenu(menu, state)
    override fun getFavoriteMenu(idDb: Int): Flow<MenuEntity> = menuRepository.getFavoriteMenu(idDb)
    override fun getFavoriteMenus(): Flow<List<Menu>> = menuRepository.getFavoriteMenus()
}