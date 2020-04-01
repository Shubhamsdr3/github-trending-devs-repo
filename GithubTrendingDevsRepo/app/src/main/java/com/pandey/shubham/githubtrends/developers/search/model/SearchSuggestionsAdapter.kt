package com.pandey.shubham.githubtrends.developers.search.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.developers.data.DevelopersDto
import kotlinx.android.synthetic.main.developer_item_view.view.*

class SearchSuggestionsAdapter(private var developerList: List<DevelopersDto>) : RecyclerView.Adapter<SearchSuggestionsAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.search_suggestion_item, parent, false)
        return SearchViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return developerList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(developerList[position])
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(developersDto: DevelopersDto) {
            Glide.with(itemView.context)
                .load(developersDto.avatarImageUrl)
                .into(itemView.avatar_image)
            itemView.user_name.text = developersDto.userName
            itemView.user_id.text = developersDto.userId
            itemView.repo_description.text = developersDto.repoDetail?.repoDescription
        }

    }
}