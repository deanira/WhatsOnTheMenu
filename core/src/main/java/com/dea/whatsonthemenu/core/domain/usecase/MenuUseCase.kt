package com.dea.whatsonthemenu.core.domain.usecase

import com.dea.whatsonthemenu.core.data.Resource
import com.dea.whatsonthemenu.core.data.source.local.entity.MenuEntity
import com.dea.whatsonthemenu.core.domain.model.Menu
import com.dea.whatsonthemenu.core.domain.model.MenuInformation
import kotlinx.coroutines.flow.Flow

interface MenuUseCase {
    fun getMenu(query: String): Flow<Resource<List<Menu>>>
    fun getMenuInformation(id: String): Flow<Resource<MenuInformation>>
    fun setFavoriteMenu(menu: Menu, state: Boolean)
    fun getFavoriteMenu(idDb: Int): Flow<MenuEntity>
    fun getFavoriteMenus(): Flow<List<Menu>>
}