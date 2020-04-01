package com.pandey.shubham.githubtrends.repositories.details.data

import android.os.Parcelable
import com.pandey.shubham.githubtrends.repositories.data.ContributorsDto
import kotlinx.android.parcel.Parcelize

/**
 * Custom Dto: to pass from one activity to other activity
 */
@Parcelize
data class RepoDetailsInfo (
    val imageUrl : String,
    val repoAuthor : String,
    val repoDescription: String,
    val language : String,
    val languageColor: String?,
    val numberOfStars :Int,
    val numberOfForks: Int
) : Parcelable