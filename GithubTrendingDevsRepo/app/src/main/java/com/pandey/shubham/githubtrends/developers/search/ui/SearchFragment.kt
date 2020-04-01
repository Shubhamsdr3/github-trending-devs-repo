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
import com.pandey.shubham.githubtrends.developers.search.model.SearchFragmentViewModel
import com.pandey.shubham.githubtrends.developers.search.model.SearchSuggestionsAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : BaseFragment() {

    @Inject
    lateinit var searchFragmentViewModel: SearchFragmentViewModel

    private lateinit var searchFilterableAdapter: SearchSuggestionsAdapter

    private var developerList = mutableListOf<DevelopersDto>()

    companion object {
        fun newInstance() : SearchFragment {
            return SearchFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_text_et.requestFocus()
        initListener()

        searchFragmentViewModel.developersLiveData.observe(viewLifecycleOwner, Observer { it ->
            developerList.clear()
            developerList.addAll(it)
            searchFilterableAdapter.notifyDataSetChanged()
        })
        searchFilterableAdapter = SearchSuggestionsAdapter(developerList)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        suggestions_list.layoutManager = layoutManager
        suggestions_list.itemAnimator = DefaultItemAnimator()
        suggestions_list.adapter = searchFilterableAdapter
    }

    private fun initListener() {
        search_text_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                p0.let {
                    if (!TextUtils.isEmpty(it)) {
                        searchFragmentViewModel.onQuerySearch(it.toString())
//                        searchFilterableAdapter.filter.filter(p0.toString())
                    } else {
                        val size = developerList.size
                        developerList.clear()
                        searchFilterableAdapter.notifyItemRangeRemoved(0, size)
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
        }
    }

    override fun getLayoutFile(): Int {
        return R.layout.fragment_search
    }
}