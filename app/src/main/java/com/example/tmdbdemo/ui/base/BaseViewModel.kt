package com.example.tmdbdemo.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(protected val compositeDisposable: CompositeDisposable) : ViewModel() {

    var messageString = MutableLiveData<String>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    abstract fun onCreate()
}