package com.example.tmdbdemo.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.tmdbdemo.data.NetworkService
import com.example.tmdbdemo.data.Networking
import com.example.tmdbdemo.data.models.response.ResultsItem
import com.example.tmdbdemo.data.repository.TMDBRepository
import com.example.tmdbdemo.ui.base.BaseViewModel
import com.example.tmdbdemo.utils.common.Resource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DashBoardViewModel(
    compositeDisposable: CompositeDisposable,
    private val tmdbRepository: TMDBRepository
) : BaseViewModel(compositeDisposable) {

    override fun onCreate() {
        fetchTrendingData()
    }

    val liveData = MutableLiveData<Resource<List<ResultsItem>>>()

    fun fetchTrendingData() {
        compositeDisposable.add(
            tmdbRepository.fetchTrendingData(Networking.api_key)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    liveData.postValue(Resource.success(it))
                }, {
                    liveData.postValue(Resource.error())
                })
        )
    }

    fun getTrendingData(): LiveData<List<ResultsItem>> {
        return Transformations.map(liveData) { it.data }
    }

}