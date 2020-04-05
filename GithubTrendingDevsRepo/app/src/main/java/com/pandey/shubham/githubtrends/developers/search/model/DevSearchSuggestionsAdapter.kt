package com.pandey.shubham.githubtrends.developers.search.model

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.base.BaseAdapter
import com.pandey.shubham.githubtrends.developers.data.DevelopersDto
import kotlinx.android.synthetic.main.search_suggestion_item.view.*


class DevSearchSuggestionsAdapter (itemList: List<DevelopersDto>) : BaseAdapter<DevelopersDto>(itemList) {

    override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
        return DeveloperSearchViewHolder(view)
    }

    override fun getLayoutId(position: Int, obj: DevelopersDto): Int {
        return R.layout.search_suggestion_item
    }

    inner class DeveloperSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),  Binder<DevelopersDto> {

        override fun bind(data: DevelopersDto) {
            Glide.with(itemView.context)
                .load(data.avatarImageUrl)
                .into(itemView.avatar_image)
            itemView.user_name.text = data.userName
            itemView.user_id.text = data.userId
            itemView.repo_description.text = data.repoDetail?.repoDescription
        }
    }
}