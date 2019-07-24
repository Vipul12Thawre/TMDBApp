package com.example.tmdbdemo.di.modules

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.tmdbdemo.data.repository.TMDBRepository
import com.example.tmdbdemo.ui.dashboard.DashBoardViewModel
import com.example.tmdbdemo.ui.search.SearchViewModel
import com.example.tmdbdemo.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(val activity: AppCompatActivity) {


    @Provides
    fun provideDashBoardViewModel(
        compositeDisposable: CompositeDisposable,
        tmdbRepository: TMDBRepository
    ): DashBoardViewModel =
        ViewModelProviders.of(
            activity, ViewModelProviderFactory(DashBoardViewModel::class) {
                DashBoardViewModel(compositeDisposable, tmdbRepository)
            }).get(DashBoardViewModel::class.java)


    @Provides
    fun provideSearchViewModel(
        compositeDisposable: CompositeDisposable,
        tmdbRepository: TMDBRepository
    ): SearchViewModel =
        ViewModelProviders.of(
            activity, ViewModelProviderFactory(SearchViewModel::class) {
                SearchViewModel(compositeDisposable, tmdbRepository)
            }).get(SearchViewModel::class.java)


}
