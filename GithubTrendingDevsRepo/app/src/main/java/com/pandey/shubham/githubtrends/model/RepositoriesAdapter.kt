package com.pandey.shubham.githubtrends.model

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.data.RepositoriesDto
import com.pandey.shubham.githubtrends.ui.repositories.data.RepoDetailsInfo
import kotlinx.android.synthetic.main.repository_item_view.view.*

class RepositoriesAdapter() : RecyclerView.Adapter<RepositoriesAdapter.RepositoriesViewHolder>() {

    lateinit var repositoriesAdapterListener: RepositoriesAdapterListener

    private lateinit var repoList: List<RepositoriesDto>

    private lateinit var mContext: Context

    constructor(context: Context, repoDtoList: List<RepositoriesDto>, repositoriesAdapterListener: RepositoriesAdapterListener) : this(){
        this.mContext = context
        this.repoList = repoDtoList
        this.repositoriesAdapterListener = repositoriesAdapterListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val inflatedView : View = LayoutInflater.from(parent.context).inflate(R.layout.repository_item_view, parent, false)
        return RepositoriesViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        holder.setData(mContext, repoList[position])
    }

    inner class RepositoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(mContext: Context, repositoriesDto: RepositoriesDto) {
            Glide.with(mContext)
                .load(repositoriesDto.imageUrl)
                .into(itemView.avatar_repo_image)
            itemView.repo_name.text = repositoriesDto.repoName
            itemView.repo_author.text = repositoriesDto.repoAuthor
            itemView.repo_descriptions.text = repositoriesDto.repoDescription
            itemView.language.text = repositoriesDto.language
            if (repositoriesDto.languageColor != null) {
                itemView.language.setTextColor(Color.parseColor(repositoriesDto.languageColor))
            }
            itemView.repo_stars.text = repositoriesDto.stars.toString()
            itemView.repo_forks.text = repositoriesDto.forks.toString()
            initListener(repositoriesDto)
        }

        private fun initListener(repositoriesDto: RepositoriesDto) {
            itemView.setOnClickListener {
                val repoDetailsInfo = RepoDetailsInfo(
                    repositoriesDto.imageUrl ?: "",
                    repositoriesDto.repoAuthor ?: "",
                    repositoriesDto.language ?: "",
                    repositoriesDto.languageColor ?: "",
                    repositoriesDto.stars ?: 0,
                    repositoriesDto.forks ?: 0,
                    repositoriesDto.contributorList
                    )
                repositoriesAdapterListener.onAdapterItemClicked(repoDetailsInfo)
            }
        }
    }

    interface RepositoriesAdapterListener {
        fun onAdapterItemClicked(repoDetailsInfo: RepoDetailsInfo)
    }
}


