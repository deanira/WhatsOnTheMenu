package com.dea.whatsonthemenu.core.data.source.remote.network

import com.dea.whatsonthemenu.core.data.source.remote.response.ListMenuResponse
import com.dea.whatsonthemenu.core.data.source.remote.response.MenuInformationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("food/menuItems/search")
    suspend fun getMenu(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String,
        @Query("number") number: Int
    ): ListMenuResponse

    @GET("food/menuItems/{id}")
    suspend fun getMenuInformation(
        @Query("apiKey") apiKey: String,
        @Path("id") id: String
    ): MenuInformationResponse
}