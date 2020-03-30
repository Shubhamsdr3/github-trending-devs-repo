package com.pandey.shubham.githubtrends.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.utils.ConnectionUtils
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.fragment_base.view.*

abstract class BaseFragment : Fragment() {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val parentView: View = inflater.inflate(R.layout.fragment_base, container, false)
        val inflatedView: View = inflater.inflate(getLayoutFile(), fragment_main_container, false)
        parentView.fragment_main_container.addView(inflatedView, 0)
        return parentView
    }

    protected abstract fun getLayoutFile(): Int
}