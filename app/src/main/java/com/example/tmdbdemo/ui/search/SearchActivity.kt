package com.example.tmdbdemo.ui.search

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbdemo.R
import com.example.tmdbdemo.data.models.response.ResultsItem
import com.example.tmdbdemo.di.components.ActivityComponent
import com.example.tmdbdemo.ui.base.BaseActivity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : BaseActivity() {

    @Inject
    lateinit var searchViewModel: SearchViewModel

    lateinit var searchMovieAdapter: SearchMoviesAdapter
    val searchList = ArrayList<ResultsItem>()

    override fun injectDependency(component: ActivityComponent) {
        component.injectActivity(this)
    }

    override fun onCreate() {
        searchViewModel.fetchTmdbSearch(edtSearch.getObservableString())
    }

    override fun setupObservers() {
        searchViewModel.getSearchedLiveData().observe(this, Observer {
            searchList.clear()
            searchList.addAll(it)
            searchMovieAdapter.notifyDataSetChanged()
        })
    }


    override fun setupViews(savedInstanceState: Bundle?) {
        searchMovieAdapter = SearchMoviesAdapter(this, searchList)
        rvSearchList.setHasFixedSize(true)
        rvSearchList.layoutManager = LinearLayoutManager(this)
        rvSearchList.adapter = searchMovieAdapter
    }

    override fun getLayoutResource(): Int = R.layout.activity_search


    private fun SearchView.getObservableString(): Observable<String> {

        val pubSub = PublishSubject.create<String>()

        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { pubSub.onNext(it) }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                pubSub.onComplete()
                return true
            }

        })
        return pubSub
    }
}
