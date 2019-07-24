package com.example.tmdbdemo.di.components

import com.example.tmdbdemo.di.modules.ActivityModule
import com.example.tmdbdemo.di.scopes.ActivityScope
import com.example.tmdbdemo.ui.dashboard.DashBoardActivity
import com.example.tmdbdemo.ui.search.SearchActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun injectActivity(dashBoardActivity: DashBoardActivity)

    fun injectActivity(searchActivity: SearchActivity)
}