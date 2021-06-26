package com.dea.whatsonthemenu.core.data.source.local

import com.dea.whatsonthemenu.core.data.source.local.entity.MenuEntity
import com.dea.whatsonthemenu.core.data.source.local.room.MenuDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor (private val menuDao: MenuDao) {
    fun getAllMenu(): Flow<List<MenuEntity>> = menuDao.getAllMenu()

    fun getMenuInformation(id: Int): Flow<MenuEntity> = menuDao.getMenuInformation(id)

    fun getFavoriteMenus(): Flow<List<MenuEntity>> = menuDao.getFavoriteMenus()

    suspend fun insertMenu(menuList: List<MenuEntity>) = menuDao.insertMenu(menuList)

    suspend fun insertMenu(menu: MenuEntity) = menuDao.insertMenu(menu)

    suspend fun updateMenu(menu: MenuEntity) = menuDao.updateMenu(menu)

    fun setFavoriteMenu(menu: MenuEntity, newState: Boolean) {
        menu.isFavorite = newState
        menuDao.updateMenu(menu)
    }

    fun getFavoriteMenu(idDb: Int) = menuDao.getMenuInformation(idDb)
}