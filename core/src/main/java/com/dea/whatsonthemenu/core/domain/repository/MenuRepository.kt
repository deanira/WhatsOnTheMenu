package com.dea.whatsonthemenu.core.domain.repository

import com.dea.whatsonthemenu.core.data.Resource
import com.dea.whatsonthemenu.core.data.source.local.entity.MenuEntity
import com.dea.whatsonthemenu.core.domain.model.Menu
import com.dea.whatsonthemenu.core.domain.model.MenuInformation
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    fun getMenu(query: String): Flow<Resource<List<Menu>>>
    fun getMenuInformation(id: Int): Flow<Resource<MenuInformation>>
    fun getFavoriteMenus(): Flow<List<Menu>>
    fun setFavoriteMenu(menu: Menu, state: Boolean)
    fun getFavoriteMenu(id: Int): Flow<MenuEntity>
}