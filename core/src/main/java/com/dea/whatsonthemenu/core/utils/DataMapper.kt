package com.dea.whatsonthemenu.core.utils

import com.dea.whatsonthemenu.core.data.source.local.entity.MenuEntity
import com.dea.whatsonthemenu.core.data.source.remote.response.ListMenuResponse
import com.dea.whatsonthemenu.core.data.source.remote.response.MenuInformationResponse
import com.dea.whatsonthemenu.core.domain.model.Menu
import com.dea.whatsonthemenu.core.domain.model.MenuInformation

object DataMapper {
    fun mapEntitiesToDomain(input: List<MenuEntity>): List<Menu> =
        input.map {
            Menu(
                it.menuId,
                it.title,
                it.restaurant,
                it.image,
                it.isFavorite
            )
        }

    fun mapResponsesToEntities(input: ListMenuResponse): List<MenuEntity> {
        val menuList = ArrayList<MenuEntity>()
        input.menuItems.map {
            val menu = MenuEntity(
                menuId = it.id,
                title = it.title,
                restaurant = it.restaurantChain,
                image = it.image!!,
                isFavorite = false,
                price = null,
                calories = null,
                protein = null,
                carbs = null,
            )
            menuList.add(menu)
        }
        return menuList
    }

    fun mapDomainToEntity(input: Menu) =
        MenuEntity(
            menuId = input.id,
            title = input.title,
            restaurant = input.restaurant,
            image = input.image,
            isFavorite = input.isFavorite,
            price = null,
            calories = null,
            protein = null,
            carbs = null,
        )

    fun mapEntityToDomainInfo(input: MenuEntity): MenuInformation =
        MenuInformation(
            input.menuId,
            input.title,
            input.restaurant,
            input.price,
            input.calories,
            input.protein,
            input.carbs,
            input.image,
            input.isFavorite
        )

    fun mapResponsesToEntitiesInfo(input: MenuInformationResponse): MenuEntity =
        MenuEntity(
            menuId = input.id!!,
            title = input.title!!,
            restaurant = input.restaurantChain!!,
            image = input.images?.get(0)!!,
            isFavorite = input.isFavorite,
            price = input.price,
            calories = input.nutrition?.calories,
            protein = input.nutrition?.protein,
            carbs = input.nutrition?.carbs
        )

    fun mapDomainToEntityInfo(input: Menu) =
        MenuEntity(
            menuId = input.id,
            title = input.title,
            restaurant = input.restaurant,
            image = input.image,
            isFavorite = input.isFavorite,
            price = null,
            calories = null,
            protein = null,
            carbs = null
        )
}