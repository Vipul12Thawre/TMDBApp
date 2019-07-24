package com.example.tmdbdemo.di.modules

import android.content.Context
import com.example.tmdbdemo.BuildConfig
import com.example.tmdbdemo.data.NetworkService
import com.example.tmdbdemo.data.Networking
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(val context: Context) {

    @Provides
    fun providesCompositeDisposable() = CompositeDisposable()

    @Singleton
    @Provides
    fun provideNetworkInstance(): NetworkService {
        return Networking.retrofitInstance(BuildConfig.BASE_URL, BuildConfig.API_KEY)
    }
}