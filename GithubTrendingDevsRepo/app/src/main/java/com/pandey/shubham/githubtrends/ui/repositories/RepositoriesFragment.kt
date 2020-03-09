package com.pandey.shubham.githubtrends.ui.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.data.RepositoriesDto
import com.pandey.shubham.githubtrends.model.RepositoriesAdapter
import com.pandey.shubham.githubtrends.ui.repositories.data.RepoDetailsInfo
import kotlinx.android.synthetic.main.fragment_trending_repo.*

class RepositoriesFragment : Fragment(),
    RepositoriesViewModel.RepositoriesViewModelListener , RepositoriesAdapter.RepositoriesAdapterListener {

    private var repositoriesList = mutableListOf<RepositoriesDto>()

    private lateinit var repositoriesAdapter: RepositoriesAdapter

    private lateinit var repositoriesViewModel: RepositoriesViewModel

    companion object {
        const val REPO_DETAILS_INTENT = "REPO_DETAILS_INTENT"
        fun newInstance() : RepositoriesFragment {
            return RepositoriesFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_trending_repo, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repositoriesViewModel = ViewModelProvider(this).get(RepositoriesViewModel::class.java)
        repositoriesViewModel.setListener(this) //FIXME:SHUBHAM
        repositoriesViewModel.fetchRepositories()
        repo_network_loader.visibility = View.VISIBLE
    }

    override fun onFetchRepositoriesSuccess(repositoriesList: List<RepositoriesDto>) {
        repo_network_loader.visibility = View.GONE
        this.repositoriesList = repositoriesList as MutableList<RepositoriesDto>
        repositoriesAdapter = context?.let { RepositoriesAdapter(it, repositoriesList, this) }!!
        repo_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        repo_list.adapter = repositoriesAdapter
    }

    override fun onFetchRepositoriesFailed(throwable: Throwable) {
        repo_network_loader.visibility = View.GONE
    }

    override fun onAdapterItemClicked(repoDetailsInfo: RepoDetailsInfo) {
        if (activity is RepositoriesFragmentListener) {
            val listener = activity as RepositoriesFragmentListener
            listener.onAdapterItemClicked(repoDetailsInfo)
        }
    }

    interface RepositoriesFragmentListener {
        fun onAdapterItemClicked(repoDetailsInfo: RepoDetailsInfo)
    }
}