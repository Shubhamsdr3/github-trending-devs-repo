package com.pandey.shubham.githubtrends.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.pandey.shubham.githubtrends.R
import kotlinx.android.synthetic.main.search_toolbar.view.*

class SearchToolbar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var searchToolbarListener: SearchToolbarListener

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.search_toolbar, this, false)
        this.addView(view)
        initListener()
    }

    fun setSearchToolbarCallback(searchToolbarListener: SearchToolbarListener) {
        this.searchToolbarListener = searchToolbarListener
    }

    fun setTitle(title: String) {
        toolbar_title.text = title
    }

    private fun initListener() {
        ic_arrow_back.setOnClickListener {
            searchToolbarListener.onBackArrowClicked()
        }

        search_icon.setOnClickListener {
            searchToolbarListener.onSearchIconClicked()
        }
    }

    fun isBackPressedEnabled(isEnabled: Boolean) {
        if (isEnabled) {
            ic_arrow_back.visibility = View.VISIBLE
        } else {
            ic_arrow_back.visibility = View.GONE
        }
    }

    interface SearchToolbarListener {

        fun onBackArrowClicked()

        fun onSearchIconClicked()

    }
}