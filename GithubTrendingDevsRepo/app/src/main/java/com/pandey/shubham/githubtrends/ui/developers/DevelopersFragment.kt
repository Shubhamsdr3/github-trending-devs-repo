package com.pandey.shubham.githubtrends.ui.developers

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
        developersViewModel = ViewModelProvider(this).get(DevelopersViewModel::class.java)
        if (ConnectionUtils.isInternetAvailable(context)) {
            developersViewModel.fetchDevelopers().observe(viewLifecycleOwner, Observer { onFetchDevelopersSuccess(it) })
            network_loader.visibility = View.VISIBLE
        }
    }

    private fun onFetchDevelopersSuccess(developersList: List<DevelopersDto>) {
        network_loader.visibility = View.GONE
        this.developersList = developersList as MutableList<DevelopersDto>
        developersAdapter = context?.let { DevelopersAdapter(it, developersList) }!!
        developers_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        developers_list.adapter = developersAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.repo_search_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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
        return super.onOptionsItemSelected(item)
    }

    override fun getLayoutFile(): Int {
        return R.layout.fragment_trending_dev
    }
}