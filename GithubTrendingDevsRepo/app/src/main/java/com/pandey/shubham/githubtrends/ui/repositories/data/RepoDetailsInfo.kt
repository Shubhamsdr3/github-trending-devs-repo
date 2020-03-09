package com.pandey.shubham.githubtrends.ui.repositories.data

import android.os.Parcelable
import com.pandey.shubham.githubtrends.data.ContributorsDto
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepoDetailsInfo (
    val imageUrl : String,
    val repoAuthor : String,
    val language : String,
    val languageColor: String?,
    val numberOfStars :Int,
    val numberOfForks: Int,
    val contributorsList: List<ContributorsDto>?
) : Parcelable