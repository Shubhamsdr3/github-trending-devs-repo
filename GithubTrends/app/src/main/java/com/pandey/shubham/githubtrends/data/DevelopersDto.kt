package com.pandey.shubham.githubtrends.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "developers")
data class DevelopersDto(

    @PrimaryKey
    @SerializedName("username")
    val userId : String,

    @SerializedName("name")
    val userName : String?,

    @SerializedName("url")
    val userProfileUrl : String?,

    @SerializedName("sponsorUrl")
    val sponsorUrl : String?,

    @SerializedName("avatar")
    val avatarImageUrl: String?,

    @Embedded
    @SerializedName("repo")
    val repoDetail: RepoDto?
) : Serializable

/**
 * Developer's repo detail
 */
data class RepoDto(
    @SerializedName("name") val repoName : String?,
    @SerializedName("description") val repoDescription: String?,
    @SerializedName("url") val repoUrl: String?
)