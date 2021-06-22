package com.capstoneproject2.favorite

import com.mysub2e.capstoneproject2.di.AppComponent
import dagger.Component

@FavoriteScope
@Component(dependencies = [AppComponent::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun appComponent(appComponent: AppComponent): Builder
        fun build(): FavoriteComponent
    }

}