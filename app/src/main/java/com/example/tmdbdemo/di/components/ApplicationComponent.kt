package com.example.tmdbdemo.di.components

import com.example.tmdbdemo.TMDBApplication
import com.example.tmdbdemo.data.NetworkService
import com.example.tmdbdemo.di.modules.ApplicationModule
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(app: TMDBApplication)
    fun getCompositeDisposable(): CompositeDisposable
    fun getNetworkService(): NetworkService
}