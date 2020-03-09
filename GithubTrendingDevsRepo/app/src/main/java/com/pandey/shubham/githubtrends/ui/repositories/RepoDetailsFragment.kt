package com.pandey.shubham.githubtrends.ui.repositories

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.pandey.shubham.githubtrends.R
import com.pandey.shubham.githubtrends.ui.repositories.data.RepoDetailsInfo
import kotlinx.android.synthetic.main.fragment_repo_details.*

class RepoDetailsFragment() : Fragment() {

    companion object {

        const val REPO_DETAILS_INFO = "REPO_DETAILS_INFO"

        fun newInstance(repoDetailsInfo: RepoDetailsInfo): RepoDetailsFragment {
            val repoDetailsFragment = RepoDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(REPO_DETAILS_INFO, repoDetailsInfo)
            repoDetailsFragment.arguments = bundle
            return repoDetailsFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo_details, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repoDetailsInfo = arguments?.getParcelable<RepoDetailsInfo>(REPO_DETAILS_INFO)
        context?.let {
            Glide.with(it)
                .load(repoDetailsInfo?.imageUrl)
                .into(repo_profile_image)
        }
        repo_detail_author.text = repoDetailsInfo?.repoAuthor
        repo_detail_lang.text = repoDetailsInfo?.language
        if (repoDetailsInfo?.languageColor != null && repoDetailsInfo.languageColor.isNotEmpty()) {
            repo_detail_lang.setTextColor(Color.parseColor(repoDetailsInfo.languageColor))
        }
        repo_stars.text = repoDetailsInfo?.numberOfStars.toString()
        repo_forks.text = repoDetailsInfo?.numberOfForks.toString()
        if (repoDetailsInfo?.contributorsList != null) {
            contributor_view.visibility = View.VISIBLE
            contributor_view.setData(repoDetailsInfo.contributorsList)
        } else {
            contributor_view.visibility = View.GONE
        }
    }
}