package com.pandey.shubham.githubtrends.paging

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Response(

        @SerializedName("articles")
        val news: List<News> = emptyList()
): Serializable

data class News(

        val title: String = "",

        @SerializedName("urlToImage")
        val image: String = ""

): Serializable
