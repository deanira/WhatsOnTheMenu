package com.dea.whatsonthemenu.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuInformationResponse(

	@field:SerializedName("images")
	val images: List<String?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("badges")
	val badges: List<String?>? = null,

	@field:SerializedName("numberOfServings")
	val numberOfServings: String? = null,

	@field:SerializedName("generatedText")
	val generatedText: String? = null,

	@field:SerializedName("restaurantChain")
	val restaurantChain: String? = null,

	@field:SerializedName("nutrition")
	val nutrition: Nutrition? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("spoonacularScore")
	val spoonacularScore: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("readableServingSize")
	val readableServingSize: String? = null,

	@field:SerializedName("servingSize")
	val servingSize: String? = null,

	@field:SerializedName("imageType")
	val imageType: String? = null,

	@field:SerializedName("breadcrumbs")
	val breadcrumbs: List<String?>? = null,

	@field:SerializedName("likes")
	val likes: Double? = null,

	var isFavorite: Boolean = false
) : Parcelable

@Parcelize
data class Nutrition(

	@field:SerializedName("caloricBreakdown")
	val caloricBreakdown: CaloricBreakdown? = null,

	@field:SerializedName("carbs")
	val carbs: String? = null,

	@field:SerializedName("protein")
	val protein: String? = null,

	@field:SerializedName("fat")
	val fat: String? = null,

	@field:SerializedName("calories")
	val calories: Double? = null,

	@field:SerializedName("nutrients")
	val nutrients: List<NutrientsItem?>? = null
) : Parcelable

@Parcelize
data class NutrientsItem(

	@field:SerializedName("amount")
	val amount: Double? = null,

	@field:SerializedName("unit")
	val unit: String? = null,

	@field:SerializedName("percentOfDailyNeeds")
	val percentOfDailyNeeds: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("title")
	val title: String? = null
) : Parcelable

@Parcelize
data class CaloricBreakdown(

	@field:SerializedName("percentCarbs")
	val percentCarbs: Double? = null,

	@field:SerializedName("percentProtein")
	val percentProtein: Double? = null,

	@field:SerializedName("percentFat")
	val percentFat: Double? = null
) : Parcelable
