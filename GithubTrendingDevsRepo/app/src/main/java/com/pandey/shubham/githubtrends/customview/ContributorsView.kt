package com.pandey.shubham.githubtrends.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.data.ContributorsDto
import kotlinx.android.synthetic.main.contributors_item_view.view.*
import kotlinx.android.synthetic.main.contributors_view.view.*

class ContributorsView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        val rootView: View =
            LayoutInflater.from(context).inflate(R.layout.contributors_view, this, false)
        this.addView(rootView)
    }

    fun setData(contributorsList: List<ContributorsDto>) {
        contributors_list.removeAllViews()
        for (contributor in contributorsList) {
            val rootView: View = LayoutInflater.from(context).inflate(R.layout.contributors_item_view, this, false)
            Glide.with(this)
                .load(contributor.imageUrl)
                .into(rootView.contributor_image)
            rootView.contributor_name.text = contributor.userName
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 0, 10, 0)
            contributors_list.addView(rootView)
        }
    }
}