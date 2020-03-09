package com.pandey.shubham.githubtrends.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.data.DevelopersDto
import kotlinx.android.synthetic.main.developer_item_view.view.*

class DevelopersAdapter() : RecyclerView.Adapter<DevelopersAdapter.DevelopersViewHolder>() {

    private lateinit var developerList: List<DevelopersDto>

    private lateinit var mContext: Context

    constructor(context: Context, developersDtoList: List<DevelopersDto>) : this() {
        this.mContext = context
        this.developerList = developersDtoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevelopersViewHolder {
        val inflatedView : View = LayoutInflater.from(parent.context).inflate(R.layout.developer_item_view, parent, false)
        return DevelopersViewHolder(
            inflatedView
        )
    }

    override fun getItemCount(): Int {
        return developerList.size
    }

    override fun onBindViewHolder(holder: DevelopersViewHolder, position: Int) {
        holder.setData(mContext, developerList[position])
    }

    class DevelopersViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun setData(mContext: Context, developerResponseDto: DevelopersDto) {
            Glide.with(mContext)
                .load(developerResponseDto.avatarImageUrl)
                .into(itemView.avatar_image)
            itemView.user_name.text = developerResponseDto.userName
            itemView.user_id.text = developerResponseDto.userId
            itemView.repo_description.text = developerResponseDto.repoDetail?.repoDescription
        }
    }
}