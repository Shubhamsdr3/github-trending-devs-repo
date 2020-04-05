package com.pandey.shubham.githubtrends.developers.search.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.base.BaseFragment
import com.pandey.shubham.githubtrends.developers.data.DevelopersDto
import com.pandey.shubham.githubtrends.developers.search.model.DevSearchSuggestionsAdapter
import com.pandey.shubham.githubtrends.developers.search.model.RepoSearchSuggestionAdapter
import com.pandey.shubham.githubtrends.developers.search.model.SearchFragmentViewModel
import com.pandey.shubham.githubtrends.repositories.data.RepositoriesDto
import com.pandey.shubham.githubtrends.utils.KeyboardUtils
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : BaseFragment() {

    @Inject
    lateinit var searchFragmentViewModel: SearchFragmentViewModel

    private lateinit var developerSearchAdapter: DevSearchSuggestionsAdapter

    private lateinit var repositorySearchAdapter: RepoSearchSuggestionAdapter

    private var developerList = mutableListOf<DevelopersDto>()

    private var repositoryList = mutableListOf<RepositoriesDto>()

    private var isDeveloperQueried : Boolean = false

    companion object {

        private const val IS_DEVELOPER = "IS_DEVELOPER";

        fun newInstance(isDeveloperQueried : Boolean) : SearchFragment {
            val searchFragment = SearchFragment()
            val bundle = Bundle()
            bundle.putBoolean(IS_DEVELOPER, isDeveloperQueried)
            searchFragment.arguments = bundle
            return searchFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_text_et.requestFocus()
        KeyboardUtils.showSoftKeyboard(activity, search_text_et)
        initListener()
        if (arguments?.getBoolean(IS_DEVELOPER) != null) {
            isDeveloperQueried = arguments?.getBoolean(IS_DEVELOPER)!!
        }

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        suggestions_list.layoutManager = layoutManager
        suggestions_list.itemAnimator = DefaultItemAnimator()

        searchFragmentViewModel.developersLiveData.observe(viewLifecycleOwner, Observer { it ->
            developerList.clear()
            developerList.addAll(it)
            developerSearchAdapter = DevSearchSuggestionsAdapter(developerList)
            suggestions_list.adapter = developerSearchAdapter
            developerSearchAdapter.notifyDataSetChanged()
        })

        searchFragmentViewModel.repositoriesLiveData.observe(viewLifecycleOwner, Observer { it ->
            repositoryList.clear()
            repositoryList.addAll(it)
            repositorySearchAdapter = RepoSearchSuggestionAdapter(repositoryList)
            suggestions_list.adapter = repositorySearchAdapter
            repositorySearchAdapter.notifyDataSetChanged()
        })
    }

    private fun initListener() {
        search_text_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                p0.let {
                    if (!TextUtils.isEmpty(it)) {
                        if (isDeveloperQueried) {
                            searchFragmentViewModel.onDevelopersQuerySearch(it.toString())
                        } else {
                            searchFragmentViewModel.onRepositoryQuerySearch(it.toString())
                        }
                    }
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //FIXME : Don't make a call when user removes all text from ediit text
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        ic_search_back.setOnClickListener {
            activity?.onBackPressed()
            activity?.overridePendingTransition(0, 0)
            KeyboardUtils.hideKeyboard(activity, search_text_et)
        }
    }

    override fun getLayoutFile(): Int {
        return R.layout.fragment_search
    }
}