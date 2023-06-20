package com.example.projectdenver.ui

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectdeltatwo.R
import com.example.projectdenver.cache.NewsEntity
import com.example.projectdenver.models.Article

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsList: List<NewsEntity> = emptyList()

    inner class NewsViewHolder(newsView: View): RecyclerView.ViewHolder(newsView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.itemView.findViewById<TextView>(R.id.author_text_view).text = news.author
        holder.itemView.findViewById<TextView>(R.id.description_tv).text = news.description
        holder.itemView.findViewById<TextView>(R.id.post_title_tv).text = news.title
    }

    fun setNews(newsList: List<NewsEntity>){
        this.newsList = newsList
        notifyItemRangeChanged(0, newsList.size)
    }
}