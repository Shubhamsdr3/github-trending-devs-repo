package com.pandey.shubham.githubtrends.developers.search.model

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.base.BaseAdapter
import com.pandey.shubham.githubtrends.developers.data.DevelopersDto
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.search_suggestion_item.*
import kotlinx.android.synthetic.main.search_suggestion_item.view.*
import kotlinx.android.synthetic.main.search_suggestion_item.view.avatar_image


class DevSearchSuggestionsAdapter (itemList: List<DevelopersDto>) : BaseAdapter<DevelopersDto>(itemList) {

    override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
        return DeveloperSearchViewHolder(view)
    }

    override fun getLayoutId(position: Int, obj: DevelopersDto): Int {
        return R.layout.search_suggestion_item
    }

    inner class DeveloperSearchViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),  Binder<DevelopersDto>, LayoutContainer {

        override fun bind(data: DevelopersDto) {
            Glide.with(itemView.context)
                .load(data.avatarImageUrl)
                .into(avatar_image)
            user_name.text = data.userName
            user_id.text = data.userId
            repo_description.text = data.repoDetail?.repoDescription
        }
    }
}