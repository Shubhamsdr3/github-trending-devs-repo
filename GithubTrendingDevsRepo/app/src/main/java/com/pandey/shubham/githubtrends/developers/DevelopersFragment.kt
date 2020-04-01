package com.pandey.shubham.githubtrends.developers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.base.BaseFragment
import com.pandey.shubham.githubtrends.customview.SearchToolbar
import com.pandey.shubham.githubtrends.developers.data.DevelopersDto
import com.pandey.shubham.githubtrends.developers.model.DevelopersAdapter
import com.pandey.shubham.githubtrends.developers.search.SearchActivity
import com.pandey.shubham.githubtrends.utils.ConnectionUtils
import kotlinx.android.synthetic.main.fragment_trending_dev.*
import timber.log.Timber
import javax.inject.Inject


class DevelopersFragment : BaseFragment(), SearchToolbar.SearchToolbarListener {

    private var developersList = mutableListOf<DevelopersDto>()

    private lateinit var developersAdapter: DevelopersAdapter

    private lateinit var networkConnectionReceiver: NetworkConnectionReceiver

    @Inject
    lateinit var developersViewModel: DevelopersViewModel

    private lateinit var developerFragmentListener: DeveloperFragmentListener

    companion object {
        fun newInstance(): DevelopersFragment {
            return DevelopersFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is DeveloperFragmentListener) {
            this.developerFragmentListener = context
        } else {
            throw RuntimeException(context.toString()
                        + "Bhai, callback to define kar de"
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dev_toolbar.setTitle(getString(R.string.developers))
        dev_toolbar.isBackPressedEnabled(false)
        if (ConnectionUtils.isInternetAvailable(context)) {
            developersViewModel.makeNetworkCall()
            network_loader.visibility = View.VISIBLE
        }
        developersViewModel.getDevelopersFromDb()?.observe(viewLifecycleOwner, Observer {
            onFetchDevelopersSuccess(it)
        })
        initListener()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        networkConnectionReceiver = NetworkConnectionReceiver()
        activity?.registerReceiver(networkConnectionReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
    }

    //handle search operations
    private fun initListener() {
        dev_toolbar.setSearchToolbarCallback(this)
    }

    private fun onFetchDevelopersSuccess(developersList: List<DevelopersDto>) {
        network_loader.visibility = View.GONE
        this.developersList = developersList as MutableList<DevelopersDto>
        developersAdapter = context?.let {
            DevelopersAdapter(
                it,
                developersList
            )
        }!!
        developers_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        developers_list.adapter = developersAdapter
    }

    override fun getLayoutFile(): Int {
        return R.layout.fragment_trending_dev
    }

    override fun onBackArrowClicked() {
        activity?.onBackPressed() //FIXME:SHUBHAM: we can remove current fragment from backstack as well
    }

    override fun onSearchIconClicked() {
        startActivity(Intent(activity, SearchActivity::class.java))
//        developerFragmentListener.onSearchClicked()
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.unregisterReceiver(networkConnectionReceiver)
    }

    inner class NetworkConnectionReceiver : BroadcastReceiver() {

        override fun onReceive(p0: Context?, p1: Intent?) {
            if (ConnectionUtils.isInternetAvailable(context)) {
                Timber.d("Network broadcast received...")
                developersViewModel.makeNetworkCall()
            }
        }
    }

    interface DeveloperFragmentListener {

        fun onSearchClicked()
    }
}