package com.pandey.shubham.githubtrends.ui.developers

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.base.BaseFragment
import com.pandey.shubham.githubtrends.data.DevelopersDto
import com.pandey.shubham.githubtrends.model.DevelopersAdapter
import com.pandey.shubham.githubtrends.utils.ConnectionUtils
import kotlinx.android.synthetic.main.fragment_trending_dev.*


class DevelopersFragment : BaseFragment() {

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
        dev_toolbar.inflateMenu(R.menu.search_menu)
        dev_toolbar.title = getString(R.string.developers)
        developersViewModel = ViewModelProvider(this).get(DevelopersViewModel::class.java)
        if (ConnectionUtils.isInternetAvailable(context)) {
            developersViewModel.fetchDevelopers().observe(viewLifecycleOwner, Observer { onFetchDevelopersSuccess(it) })
            network_loader.visibility = View.VISIBLE
        }
        initListener()
    }

    //handle search operations
    private fun initListener() {
        dev_toolbar.setOnMenuItemClickListener(object : androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                if (item?.itemId == R.id.repo_search_action) {
                    val searchView = item.actionView as SearchView
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            developersAdapter.filter.filter(newText)
                            return false
                        }
                    })
                    return true
                }
                return false
            }
        })
    }

    private fun onFetchDevelopersSuccess(developersList: List<DevelopersDto>) {
        network_loader.visibility = View.GONE
        this.developersList = developersList as MutableList<DevelopersDto>
        developersAdapter = context?.let { DevelopersAdapter(it, developersList) }!!
        developers_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        developers_list.adapter = developersAdapter
    }

    override fun getLayoutFile(): Int {
        return R.layout.fragment_trending_dev
    }
}