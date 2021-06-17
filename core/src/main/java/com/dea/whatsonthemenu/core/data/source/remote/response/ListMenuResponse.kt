package com.dea.whatsonthemenu.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListMenuResponse(

	@field:SerializedName("totalMenuItems")
	val totalMenuItems: Int? = null,

	@field:SerializedName("number")
	val number: Int? = null,

	@field:SerializedName("expires")
	val expires: Long? = null,

	@field:SerializedName("offset")
	val offset: Int? = null,

	@field:SerializedName("processingTimeMs")
	val processingTimeMs: Int? = null,

	@field:SerializedName("menuItems")
	val menuItems: List<MenuItemsItem>,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable

@Parcelize
data class MenuItemsItem(

	@field:SerializedName("restaurantChain")
	val restaurantChain: String,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("readableServingSize")
	val readableServingSize: String? = null,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("servingSize")
	val servingSize: String? = null,

	@field:SerializedName("imageType")
	val imageType: String? = null
) : Parcelable
