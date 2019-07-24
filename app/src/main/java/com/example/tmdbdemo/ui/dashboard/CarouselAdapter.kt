package com.example.tmdbdemo.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbdemo.R
import com.example.tmdbdemo.data.models.response.ResultsItem
import com.example.tmdbdemo.utils.common.GlideApp

class CarouselAdapter(val context: Context, val trendingList: List<ResultsItem>) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return CarouselViewHolder(itemView)
    }

    override fun getItemCount(): Int = trendingList.size


    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        GlideApp.with(context)
            .load("https://image.tmdb.org/t/p/w500${trendingList[position].poster_path}")
            .into(holder.imgBanner)
    }

    class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgBanner = itemView.findViewById<ImageView>(R.id.imgBanner)
    }
}