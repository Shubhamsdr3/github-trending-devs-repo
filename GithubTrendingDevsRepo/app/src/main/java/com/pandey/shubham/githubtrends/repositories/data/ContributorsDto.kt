package com.pandey.shubham.githubtrends.repositories.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "contributors")
data class ContributorsDto(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @SerializedName("username")
    val userName: String?,

    @SerializedName("href")
    val userProfile: String?,

    @SerializedName("avatar")
    val imageUrl: String?

) : Parcelable