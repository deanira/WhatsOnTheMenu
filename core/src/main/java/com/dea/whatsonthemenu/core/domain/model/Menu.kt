package com.dea.whatsonthemenu.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Menu(
    val id: Int,
    val title: String,
    val restaurant: String,
    val image: String,
    val isFavorite: Boolean
): Parcelable
