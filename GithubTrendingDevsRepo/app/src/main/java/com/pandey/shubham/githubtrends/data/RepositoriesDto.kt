package com.pandey.shubham.githubtrends.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class RepositoriesDto  (
    @SerializedName("author") val repoAuthor : String?,
    @SerializedName("name") val repoName: String?,
    @SerializedName("avatar") val imageUrl: String?,
    @SerializedName("url") val repoUrl: String?,
    @SerializedName("description") val repoDescription: String?,
    @SerializedName("language") val language: String?,
    @SerializedName("languageColor") val languageColor: String?,
    @SerializedName("stars") val stars: Int?,
    @SerializedName("forks") val forks: Int?,
    @SerializedName("currentPeriodStars") val currentPeriodStars: Int?,
    @SerializedName("builtBy") val contributorList: List<ContributorsDto>?
    ) : Serializable

@Parcelize
data class ContributorsDto(
    @SerializedName("username") val userName: String?,
    @SerializedName("href") val userProfile: String?,
    @SerializedName("avatar") val imageUrl: String?
    ) : Parcelable