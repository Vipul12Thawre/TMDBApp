package com.example.tmdbdemo.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbdemo.R
import com.example.tmdbdemo.data.models.response.ResultsItem
import com.example.tmdbdemo.di.components.ActivityComponent
import com.example.tmdbdemo.ui.base.BaseActivity
import com.example.tmdbdemo.ui.search.SearchActivity
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoardActivity : BaseActivity<DashBoardViewModel>() {


    private lateinit var carouselAdapter: CarouselAdapter
    val trendingList = ArrayList<ResultsItem>()


    override fun injectDependency(component: ActivityComponent) {
        component.injectActivity(this)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        addViewListeners()
        carouselAdapter = CarouselAdapter(this, trendingList)
        rvTrend.setHasFixedSize(true)
        rvTrend.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvTrend.adapter = carouselAdapter
    }

    private fun addViewListeners() {
        cvSearch.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }

    override fun getLayoutResource(): Int = R.layout.activity_dash_board

    override fun setupObservers() {

        viewModel.getTrendingData().observe(this, Observer {
            trendingList.addAll(it)
            carouselAdapter.notifyDataSetChanged()
        })
    }
}
