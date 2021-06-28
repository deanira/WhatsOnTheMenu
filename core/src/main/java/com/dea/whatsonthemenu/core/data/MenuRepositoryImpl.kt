package com.dea.whatsonthemenu.core.data

import com.dea.whatsonthemenu.core.data.source.local.LocalDataSource
import com.dea.whatsonthemenu.core.data.source.local.entity.MenuEntity
import com.dea.whatsonthemenu.core.data.source.remote.RemoteDataSource
import com.dea.whatsonthemenu.core.data.source.remote.network.ApiResponse
import com.dea.whatsonthemenu.core.data.source.remote.response.ListMenuResponse
import com.dea.whatsonthemenu.core.data.source.remote.response.MenuInformationResponse
import com.dea.whatsonthemenu.core.domain.model.Menu
import com.dea.whatsonthemenu.core.domain.model.MenuInformation
import com.dea.whatsonthemenu.core.domain.repository.MenuRepository
import com.dea.whatsonthemenu.core.utils.AppExecutors
import com.dea.whatsonthemenu.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MenuRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MenuRepository {

    override fun getMenu(query: String): Flow<Resource<List<Menu>>> =
        object : NetworkBoundResource<List<Menu>, ListMenuResponse>() {
            override fun loadFromDB(): Flow<List<Menu>> {
                return localDataSource.getAllMenu().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Menu>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<ListMenuResponse>> =
                remoteDataSource.getMenu(query)

            override suspend fun saveCallResult(data: ListMenuResponse) {
                val menuList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMenu(menuList)
            }
        }.asFlow()

//    override fun getMenuInformation(id: Int): Flow<Resource<MenuInformation>> =
//        remoteDataSource.getMenuInformation(id)

    override fun getMenuInformation(id: Int): Flow<Resource<MenuInformation>> =
        object : NetworkBoundResource<MenuInformation, MenuInformationResponse>() {
            override fun loadFromDB(): Flow<MenuInformation> {
                return localDataSource.getMenuInformation(id).map {
                    DataMapper.mapEntityToDomainInfo(it)
                }
            }

            override fun shouldFetch(data: MenuInformation?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<MenuInformationResponse>> =
                remoteDataSource.getMenuInformation(id)

            override suspend fun saveCallResult(data: MenuInformationResponse) {
                val menu = DataMapper.mapResponsesToEntitiesInfo(data)
                localDataSource.updateMenu(menu)
            }
        }.asFlow()

    override fun getFavoriteMenus(): Flow<List<Menu>> {
        return localDataSource.getFavoriteMenus().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMenu(menu: Menu, state: Boolean) {
        val menuEntity = DataMapper.mapDomainToEntity(menu)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMenu(menuEntity, state) }
    }

    override fun getFavoriteMenu(id: Int): Flow<MenuEntity> = localDataSource.getFavoriteMenu(id)
}