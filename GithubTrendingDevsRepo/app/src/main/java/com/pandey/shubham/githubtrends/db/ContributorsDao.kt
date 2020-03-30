package com.pandey.shubham.githubtrends.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pandey.shubham.githubtrends.repositories.data.ContributorsDto

@Dao
interface ContributorsDao {

    @Insert
    fun insertContributor(contributorsDto: ContributorsDto)

    @Insert
    fun insertAllContributors(developersList: List<ContributorsDto>)

    @Query("SELECT * FROM contributors")
    fun getAllDevelopers(): LiveData<List<ContributorsDto>>

    @Query("SELECT * FROM contributors WHERE userName = :userName")
    fun getContributor(userName: String): ContributorsDto
}