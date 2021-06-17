package com.dea.whatsonthemenu.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dea.whatsonthemenu.core.data.source.local.entity.MenuEntity

@Database(entities = [MenuEntity::class], version = 1, exportSchema = false)
abstract class MenuDatabase: RoomDatabase() {
    abstract fun menuDao(): MenuDao
}