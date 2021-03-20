package com.pandey.shubham.githubtrends.paging

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.paging.data.State
import kotlinx.android.synthetic.main.item_list_footer.view.*
import kotlinx.android.synthetic.main.item_news.view.*

class ListFooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

    fun bind(status: State?) {
        itemView.progress_bar.visibility = if (status == State.LOADING) VISIBLE else View.GONE
        itemView.txt_error.visibility = if (status == State.ERROR) VISIBLE else View.GONE
    }

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): ListFooterViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_footer, parent, false)
            view.txt_error.setOnClickListener { retry() }
            return ListFooterViewHolder(view)
        }
    }
}

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(news: News?) {
        if (news != null) {
            itemView.txt_news_name.text = news.title
            Glide.with(itemView.context)
                    .load(news.image)
                    .into(itemView.img_news_banner)
        }
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
            return NewsViewHolder(view)
        }
    }
}