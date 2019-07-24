package com.example.tmdbdemo.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.example.tmdbdemo.R
import com.example.tmdbdemo.data.models.response.ResultsItem
import com.example.tmdbdemo.di.components.ActivityComponent
import com.example.tmdbdemo.ui.base.BaseActivity
import com.example.tmdbdemo.ui.search.SearchActivity
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.activity_dash_board.*
import javax.inject.Inject

class DashBoardActivity : BaseActivity() {

    @Inject
    lateinit var dashBoardViewModel: DashBoardViewModel


    private lateinit var carouselAdapter: CarouselAdapter
    val trendingList = ArrayList<ResultsItem>()

    override fun onCreate() {
        dashBoardViewModel.fetchTrendingData()
    }

    override fun injectDependency(component: ActivityComponent) {
        component.injectActivity(this)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        addViewListeners()
        carouselAdapter = CarouselAdapter(this, trendingList)
        rvTrend.setHasFixedSize(true)
        rvTrend.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvTrend.adapter = carouselAdapter
    }

    private fun addViewListeners() {
        cvSearch.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }

    override fun getLayoutResource(): Int = R.layout.activity_dash_board

    override fun setupObservers() {

        dashBoardViewModel.getTrendingData().observe(this, Observer {
            trendingList.addAll(it)
            carouselAdapter.notifyDataSetChanged()
        })
    }
}
