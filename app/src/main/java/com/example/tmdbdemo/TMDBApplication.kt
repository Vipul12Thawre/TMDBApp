package com.example.tmdbdemo

import android.app.Application
import com.example.tmdbdemo.di.components.ApplicationComponent
import com.example.tmdbdemo.di.components.DaggerApplicationComponent
import com.example.tmdbdemo.di.modules.ApplicationModule

class TMDBApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent
            .builder().applicationModule(ApplicationModule(this))
            .build()
        component.inject(this)
    }
}