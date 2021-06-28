package com.dea.whatsonthemenu.core.data.source.remote

import android.util.Log
import com.dea.whatsonthemenu.core.BuildConfig
import com.dea.whatsonthemenu.core.data.source.remote.network.ApiResponse
import com.dea.whatsonthemenu.core.data.source.remote.network.ApiService
import com.dea.whatsonthemenu.core.data.source.remote.response.ListMenuResponse
import com.dea.whatsonthemenu.core.data.source.remote.response.MenuInformationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getMenu(query: String): Flow<ApiResponse<ListMenuResponse>> {
        return flow {
            try {
                val response = apiService.getMenu(BuildConfig.API_KEY, query, 20)
                if (response.menuItems.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMenuInformation(id: Int): Flow<ApiResponse<MenuInformationResponse>> {
        return flow {
            try {
                val response = apiService.getMenuInformation(BuildConfig.API_KEY, id.toString())
                if (response.title?.isNotEmpty() == true) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}