package com.dea.whatsonthemenu.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "menu")
data class MenuEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "menuId")
    var menuId: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "restaurant")
    var restaurant: String,

    @ColumnInfo(name = "price")
    var price: String?,

    @ColumnInfo(name = "calories")
    var calories: Double?,

    @ColumnInfo(name = "protein")
    var protein: String?,

    @ColumnInfo(name = "carbs")
    var carbs: String?,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false

): Parcelable
