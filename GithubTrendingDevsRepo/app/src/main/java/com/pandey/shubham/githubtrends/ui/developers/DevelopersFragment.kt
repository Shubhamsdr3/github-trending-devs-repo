package com.pandey.shubham.githubtrends.ui.developers

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.base.BaseFragment
import com.pandey.shubham.githubtrends.data.DevelopersDto
import com.pandey.shubham.githubtrends.model.DevelopersAdapter
import com.pandey.shubham.githubtrends.utils.ConnectionUtils
import kotlinx.android.synthetic.main.fragment_trending_dev.*

class DevelopersFragment : BaseFragment(),
    DevelopersViewModel.DevelopersViewModelListener {

    private var developersList = mutableListOf<DevelopersDto>()

    private lateinit var developersAdapter: DevelopersAdapter

    private lateinit var developersViewModel: DevelopersViewModel

    companion object {
        fun newInstance(): DevelopersFragment {
            return DevelopersFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        developersViewModel = ViewModelProvider(this).get(DevelopersViewModel::class.java)
        developersViewModel.setListener(this) //FIXME:SHUBHAM
        if (ConnectionUtils.isInternetAvailable(context)) {
            developersViewModel.fetchDevelopers()
            network_loader.visibility = View.VISIBLE
        }
    }

    override fun onFetchDevelopersSuccess(developersList: List<DevelopersDto>) {
        network_loader.visibility = View.GONE
        this.developersList = developersList as MutableList<DevelopersDto>
        developersAdapter = context?.let { DevelopersAdapter(it, developersList) }!!
        developers_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        developers_list.adapter = developersAdapter
    }

    fun onDeveloperSearch(newText: String) {
        developersAdapter.filter.filter(newText)
    }

    override fun getLayoutFile(): Int {
        return R.layout.fragment_trending_dev
    }

    override fun onFetchDevelopersFailed(throwable: Throwable) {
        network_loader.visibility = View.GONE
    }

}