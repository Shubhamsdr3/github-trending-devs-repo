package com.pandey.shubham.githubtrends.ui.developers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.data.DevelopersDto
import com.pandey.shubham.githubtrends.model.DevelopersAdapter
import kotlinx.android.synthetic.main.fragment_trending_dev.*

class DevelopersFragment : Fragment(),
    DevelopersViewModel.DevelopersViewModelListener {

    private var developersList = mutableListOf<DevelopersDto>()

    private lateinit var developersAdapter: DevelopersAdapter

    private lateinit var developersViewModel: DevelopersViewModel

    companion object {
        fun newInstance(): DevelopersFragment {
            return DevelopersFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_trending_dev, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        developersViewModel = ViewModelProvider(this).get(DevelopersViewModel::class.java)
        developersViewModel.setListener(this) //FIXME:SHUBHAM
        developersViewModel.fetchDevelopers()
        network_loader.visibility = View.VISIBLE
    }

    override fun onFetchDevelopersSuccess(developersList: List<DevelopersDto>) {
        network_loader.visibility = View.GONE
        this.developersList = developersList as MutableList<DevelopersDto>
        developersAdapter = context?.let { DevelopersAdapter(it, developersList) }!!
        developers_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        developers_list.adapter = developersAdapter
    }

    override fun onFetchDevelopersFailed(throwable: Throwable) {
        network_loader.visibility = View.GONE
    }

}