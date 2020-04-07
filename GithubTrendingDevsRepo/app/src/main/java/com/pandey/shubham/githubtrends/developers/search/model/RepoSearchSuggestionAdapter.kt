package com.pandey.shubham.githubtrends.developers.search.model

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.base.BaseAdapter
import com.pandey.shubham.githubtrends.repositories.data.RepositoriesDto
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.repository_search_suggestion_item.*

class RepoSearchSuggestionAdapter(repositoryList: List<RepositoriesDto>) : BaseAdapter<RepositoriesDto>(repositoryList) {

    override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
        return RepositorySearchViewHolder(view)
    }

    override fun getLayoutId(position: Int, obj: RepositoriesDto): Int {
        return R.layout.repository_search_suggestion_item
    }

    inner class RepositorySearchViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), Binder<RepositoriesDto>, LayoutContainer {

        override fun bind(data: RepositoriesDto) {
            Glide.with(itemView.context)
                .load(data.imageUrl)
                .into(avatar_repo_image)
            repo_author.text = data.repoAuthor
            repo_descriptions.text = data.repoDescription
            language.text = data.language
        }
    }
}