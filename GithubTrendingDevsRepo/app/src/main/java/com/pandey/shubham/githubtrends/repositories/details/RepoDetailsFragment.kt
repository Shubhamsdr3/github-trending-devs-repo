package com.pandey.shubham.githubtrends.repositories.details

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.base.BaseFragment
import com.pandey.shubham.githubtrends.repositories.details.data.RepoDetailsInfo
import kotlinx.android.synthetic.main.fragment_repo_details.*

class RepoDetailsFragment : BaseFragment() {

    companion object {

        const val REPO_DETAILS_INFO = "REPO_DETAILS_INFO"

        fun newInstance(repoDetailsInfo: RepoDetailsInfo): RepoDetailsFragment {
            val repoDetailsFragment =
                RepoDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(REPO_DETAILS_INFO, repoDetailsInfo)
            repoDetailsFragment.arguments = bundle
            return repoDetailsFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        details_toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        val repoDetailsInfo = arguments?.getParcelable<RepoDetailsInfo>(REPO_DETAILS_INFO)
        initView(repoDetailsInfo)
        // on back pressed
        details_toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initView(repoDetailsInfo: RepoDetailsInfo?) {
        context?.let {
            Glide.with(it)
                .load(repoDetailsInfo?.imageUrl)
                .into(repo_profile_image)
        }
        repo_detail_author.text = repoDetailsInfo?.repoAuthor
        details_toolbar.title = repoDetailsInfo?.repoAuthor
        repo_detail_lang.text = repoDetailsInfo?.language
        repo_detail_description.text = repoDetailsInfo?.repoDescription
        if (repoDetailsInfo?.languageColor != null && repoDetailsInfo.languageColor.isNotEmpty()) {
            repo_detail_lang.setTextColor(Color.parseColor(repoDetailsInfo.languageColor))
        }
        repo_stars.text = repoDetailsInfo?.numberOfStars.toString()
        repo_forks.text = repoDetailsInfo?.numberOfForks.toString()
//        if (repoDetailsInfo?.contributorsList != null) {
//            contributor_view.visibility = View.VISIBLE
//            contributor_view.setData(repoDetailsInfo.contributorsList)
//        } else {
//            contributor_view.visibility = View.GONE
//        }
    }

    override fun getLayoutFile(): Int {
        return R.layout.fragment_repo_details
    }
}