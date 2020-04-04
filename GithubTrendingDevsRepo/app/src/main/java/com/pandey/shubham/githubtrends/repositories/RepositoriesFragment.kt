package com.pandey.shubham.githubtrends.repositories

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.base.BaseFragment
import com.pandey.shubham.githubtrends.base.GlobalConstants
import com.pandey.shubham.githubtrends.customview.SearchToolbar
import com.pandey.shubham.githubtrends.developers.search.SearchActivity
import com.pandey.shubham.githubtrends.repositories.data.RepositoriesDto
import com.pandey.shubham.githubtrends.repositories.details.data.RepoDetailsInfo
import com.pandey.shubham.githubtrends.repositories.model.RepositoriesAdapter
import com.pandey.shubham.githubtrends.utils.ConnectionUtils
import kotlinx.android.synthetic.main.fragment_trending_repo.*
import timber.log.Timber
import javax.inject.Inject

class RepositoriesFragment : BaseFragment(), RepositoriesAdapter.RepositoriesAdapterListener, SearchToolbar.SearchToolbarListener {

    private var repositoriesList = mutableListOf<RepositoriesDto>()

    private var repositoriesAdapter: RepositoriesAdapter? = null

    @Inject
    lateinit var repositoriesViewModel: RepositoriesViewModel

    companion object {
        fun newInstance() : RepositoriesFragment {
            return RepositoriesFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repo_search_toolbar.setTitle( getString(R.string.repositories))
        repo_search_toolbar.isBackPressedEnabled(false)
        if (ConnectionUtils.isInternetAvailable(context)) {
            repo_network_loader.visibility = View.VISIBLE
            repositoriesViewModel.fetchRepositoriesFromNetwork()
        }
        repositoriesViewModel.getRepositoriesFromDb().observe(viewLifecycleOwner, Observer {
            onFetchRepositoriesSuccess(it)
        })
        initListener()
    }

    private fun initListener() {
        repo_search_toolbar.setSearchToolbarCallback(this)
        repo_swipe_refresh_container.visibility = View.VISIBLE
        repo_swipe_refresh_container.setOnRefreshListener {
            repositoriesViewModel.fetchRepositoriesFromNetwork()
        }
        repo_swipe_refresh_container.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light)
    }

    private fun onFetchRepositoriesSuccess(repositoriesList: List<RepositoriesDto>) {
        repo_network_loader.visibility = View.GONE
        repo_swipe_refresh_container.isRefreshing = false
        this.repositoriesList.clear()
        this.repositoriesList = repositoriesList as MutableList<RepositoriesDto>
        repositoriesAdapter = context?.let { RepositoriesAdapter(it, repositoriesList, this) }!!
        repo_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        repo_list.adapter = repositoriesAdapter
    }

    override fun onAdapterItemClicked(repoDetailsInfo: RepoDetailsInfo) {
        if (activity is RepositoriesFragmentListener) {
            val listener = activity as RepositoriesFragmentListener
            listener.onAdapterItemClicked(repoDetailsInfo)
        }
    }

    override fun getLayoutFile(): Int {
        return R.layout.fragment_trending_repo
    }

    inner class NetworkConnectionReceiver : BroadcastReceiver() {

        override fun onReceive(p0: Context?, p1: Intent?) {
            if (ConnectionUtils.isInternetAvailable(context)) {
                Timber.d("Network broadcast received...")
                repositoriesViewModel.fetchRepositoriesFromNetwork()
            }
        }
    }

    interface RepositoriesFragmentListener {
        fun onAdapterItemClicked(repoDetailsInfo: RepoDetailsInfo)
    }

    override fun onBackArrowClicked() {
        TODO()
    }

    override fun onSearchIconClicked() {
        val intent = Intent(activity, SearchActivity::class.java)
        intent.putExtra(GlobalConstants.REPOSITORY_QUERIES, false)
        startActivity(intent)
    }
}