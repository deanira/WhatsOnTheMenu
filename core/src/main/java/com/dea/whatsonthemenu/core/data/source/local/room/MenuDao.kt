package com.dea.whatsonthemenu.core.data.source.local.room

import androidx.room.*
import com.dea.whatsonthemenu.core.data.source.local.entity.MenuEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuDao {
    @Query("SELECT * FROM menu")
    fun getAllMenu(): Flow<List<MenuEntity>>

    @Query("SELECT * FROM menu where menuId=:id")
    fun getMenuInformation(id: Int): Flow<MenuEntity>

    @Query("SELECT * FROM menu where isFavorite = 1")
    fun getFavoriteMenu(): Flow<List<MenuEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenu(Menu: List<MenuEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenu(Menu: MenuEntity)

    @Update
    fun updateFavoriteMenu(Menu: MenuEntity)
}