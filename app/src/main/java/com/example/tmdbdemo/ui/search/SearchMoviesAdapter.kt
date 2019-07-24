package com.example.tmdbdemo.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbdemo.R
import com.example.tmdbdemo.data.models.response.ResultsItem
import com.example.tmdbdemo.utils.common.GlideApp
import com.google.android.material.textview.MaterialTextView

class SearchMoviesAdapter(val context: Context, val trendingList: List<ResultsItem>) :
    RecyclerView.Adapter<SearchMoviesAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_search_item, parent, false)
        return SearchViewHolder(itemView)
    }

    override fun getItemCount(): Int = trendingList.size


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        GlideApp.with(context)
            .load("https://image.tmdb.org/t/p/w500${trendingList[position].poster_path}")
            .into(holder.imgBanner)

        trendingList[position].original_title.let {
            holder.txtTitle.text = trendingList[position].original_title
        }
        holder.txtOverview.text = trendingList[position].overview
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgBanner = itemView.findViewById<ImageView>(R.id.imgBanner)
        val txtTitle = itemView.findViewById<MaterialTextView>(R.id.txtTitle)
        val txtOverview = itemView.findViewById<MaterialTextView>(R.id.txtOverview)
    }
}