package com.dea.whatsonthemenu.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuInformation(
    val id: Int,
    val title: String,
    val restaurant: String,
    var price: String?,
    var calories: Double?,
    var protein: String?,
    var carbs: String?,
    val image: String,
    val isFavorite: Boolean
): Parcelable
