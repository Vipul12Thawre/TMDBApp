package com.example.tmdbdemo.ui.search

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.tmdbdemo.data.Networking
import com.example.tmdbdemo.data.models.response.ResultsItem
import com.example.tmdbdemo.data.repository.TMDBRepository
import com.example.tmdbdemo.utils.common.Resource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_search.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val compositeDisposable: CompositeDisposable,
    private val tmdbRepository: TMDBRepository
) : ViewModel() {

    val searchLiveData = MutableLiveData<Resource<List<ResultsItem>>>()

    fun fetchTmdbSearch(query: Observable<String>) {

        compositeDisposable.add(
            query.debounce(300, TimeUnit.MILLISECONDS)
                .filter {
                    return@filter it.isNotEmpty()
                }.switchMapSingle {
                    tmdbRepository.fetchTmdbSearch(Networking.api_key, it)
                }
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    searchLiveData.postValue(
                        Resource.success(it)
                    )
                }, {


                })

        )
    }

    fun getSearchedLiveData(): LiveData<List<ResultsItem>> {
        return Transformations.map(searchLiveData) { it.data }
    }


}