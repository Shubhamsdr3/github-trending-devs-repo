package com.pandey.shubham.githubtrends.ui.repositories

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.base.BaseFragment
import com.pandey.shubham.githubtrends.data.RepositoriesDto
import com.pandey.shubham.githubtrends.model.RepositoriesAdapter
import com.pandey.shubham.githubtrends.ui.repositories.data.RepoDetailsInfo
import com.pandey.shubham.githubtrends.utils.ConnectionUtils
import kotlinx.android.synthetic.main.fragment_trending_repo.*

class RepositoriesFragment : BaseFragment(), RepositoriesAdapter.RepositoriesAdapterListener {

    private var repositoriesList = mutableListOf<RepositoriesDto>()

    private var repositoriesAdapter: RepositoriesAdapter? = null

    private lateinit var repositoriesViewModel: RepositoriesViewModel

    companion object {
        const val REPO_DETAILS_INTENT = "REPO_DETAILS_INTENT"
        fun newInstance() : RepositoriesFragment {
            return RepositoriesFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repo_toolbar.inflateMenu(R.menu.search_menu)
        repo_toolbar.title = getString(R.string.repositories)
        repositoriesViewModel = ViewModelProvider(this).get(RepositoriesViewModel::class.java)
        if (ConnectionUtils.isInternetAvailable(context)) {
            repositoriesViewModel.fetchRepositories().observe(viewLifecycleOwner, Observer { onFetchRepositoriesSuccess(it) })
            repo_network_loader.visibility = View.VISIBLE
        }
        initListener()
    }

    private fun initListener() {
        repo_toolbar.setOnMenuItemClickListener(object : androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                if (item?.itemId == R.id.repo_search_action) {
                    val searchView = item.actionView as SearchView
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            repositoriesAdapter?.filter?.filter(newText)
                            return false
                        }
                    })
                    return true
                }
                return false
            }
        })
    }

    private fun onFetchRepositoriesSuccess(repositoriesList: List<RepositoriesDto>) {
        repo_network_loader.visibility = View.GONE
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

    interface RepositoriesFragmentListener {
        fun onAdapterItemClicked(repoDetailsInfo: RepoDetailsInfo)
    }
}