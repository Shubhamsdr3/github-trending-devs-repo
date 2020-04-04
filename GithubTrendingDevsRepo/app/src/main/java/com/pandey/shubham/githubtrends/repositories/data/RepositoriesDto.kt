package com.pandey.shubham.githubtrends.repositories.data

import androidx.room.PrimaryKey
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.pandey.shubham.githubtrends.base.BaseResponseDto
import com.pandey.shubham.githubtrends.repositories.ContributorsConverter
import java.io.Serializable

@Entity(tableName = "repositories")
@TypeConverters(ContributorsConverter::class)
data class RepositoriesDto  (

    @PrimaryKey
    val id: Long,

    @SerializedName("author")
    val repoAuthor : String?,

    @SerializedName("name")
    val repoName: String?,

    @SerializedName("avatar")
    val imageUrl: String?,

    @SerializedName("url")
    val repoUrl: String?,

    @SerializedName("description")
    val repoDescription: String?,

    @SerializedName("language")
    val language: String?,

    @SerializedName("languageColor")
    val languageColor: String?,

    @SerializedName("stars")
    val stars: Int?,

    @SerializedName("forks")
    val forks: Int?,

    @SerializedName("currentPeriodStars")
    val currentPeriodStars: Int?

) : BaseResponseDto() , Serializable