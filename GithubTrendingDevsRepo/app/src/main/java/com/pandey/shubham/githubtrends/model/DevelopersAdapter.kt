package com.pandey.shubham.githubtrends.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.data.DevelopersDto
import com.pandey.shubham.githubtrends.data.RepositoriesDto
import kotlinx.android.synthetic.main.developer_item_view.view.*
import java.util.*

class DevelopersAdapter() : RecyclerView.Adapter<DevelopersAdapter.DevelopersViewHolder>(), Filterable {

    private var developerList = mutableListOf<DevelopersDto>()

    private lateinit var mContext: Context

    private lateinit var developersListFull: List<DevelopersDto>

    constructor(context: Context, developersDtoList: List<DevelopersDto>) : this() {
        this.mContext = context
        this.developerList = developersDtoList as MutableList<DevelopersDto>
        this.developersListFull = ArrayList(developersDtoList)
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

    override fun getFilter(): Filter {
        return object : Filter() {
            // Performs on background thread
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<DevelopersDto>()
                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(developersListFull)
                } else {
                    val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()
                    for (developerDto in developersListFull) {
                        if (developerDto.userName != null && developerDto.userName.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                            filteredList.add(developerDto)
                        }
                    }
                }
                val filterResult = FilterResults()
                filterResult.values = filteredList
                return filterResult
            }
            //on UI thread
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                developerList.clear()
                developerList.addAll(results?.values as List<DevelopersDto>)
                notifyDataSetChanged()
            }
        }
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