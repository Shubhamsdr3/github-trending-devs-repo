package com.pandey.shubham.githubtrends.paging

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.pandey.shubham.githubtrends.base.v2.IBaseFragment
import com.pandey.shubham.githubtrends.databinding.FragmentNewsListBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class NewsListFragment : IBaseFragment<FragmentNewsListBinding, NewsListViewModel>() {

    override val viewModel: NewsListViewModel by lazy {
        ViewModelProvider(this).get(NewsListViewModel::class.java)
    }

    companion object {

        fun newInstance(): NewsListFragment {
            return NewsListFragment()
        }
    }

    override fun setupObserver() {

    }

    override fun setupView() {

    }

    override fun getViewBinding(inflater: LayoutInflater): FragmentNewsListBinding {
        return FragmentNewsListBinding.inflate(inflater)
    }
}