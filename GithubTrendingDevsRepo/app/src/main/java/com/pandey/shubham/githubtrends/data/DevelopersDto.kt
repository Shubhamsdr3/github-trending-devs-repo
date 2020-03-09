package com.pandey.shubham.githubtrends.data

import com.google.gson.annotations.SerializedName

data class DevelopersDto(
    @SerializedName("username") val userId : String?,
    @SerializedName("name") val userName : String?,
    @SerializedName("url") val userProfileUrl : String?,
    @SerializedName("sponsorUrl") val sponsorUrl : String?,
    @SerializedName("avatar") val avatarImageUrl: String?,
    @SerializedName("repo") val repoDetail: RepoDto?
)

data class RepoDto(
    @SerializedName("name") val repoName : String?,
    @SerializedName("description") val repoDescription: String?,
    @SerializedName("url") val repoUrl: String?
)