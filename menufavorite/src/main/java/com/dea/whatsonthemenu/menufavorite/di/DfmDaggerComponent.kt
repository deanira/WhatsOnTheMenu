package com.dea.whatsonthemenu.menufavorite.di

import com.dea.whatsonthemenu.di.DynamicFeatureDependencies
import com.dea.whatsonthemenu.menufavorite.ui.FavoriteActivity
import dagger.Component

@Component(dependencies = [DynamicFeatureDependencies::class])
interface DfmDaggerComponent {
    fun inject(activity: FavoriteActivity)

    @Component.Factory
    interface Factory {
        fun create(dependencies: DynamicFeatureDependencies): DfmDaggerComponent
    }
}