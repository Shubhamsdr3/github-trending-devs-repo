package com.pandey.shubham.githubtrends.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pandey.shubham.githubtrends.developers.data.DevelopersDto

@Dao
interface DevelopersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDeveloper(developerDbObject: DevelopersDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDevelopers(developersList: List<DevelopersDto>)

    @Query("SELECT * FROM developers")
    fun getAllDevelopers(): LiveData<List<DevelopersDto>>

    @Query("SELECT * FROM developers WHERE userName = :userName")
    fun getDeveloper(userName: String): DevelopersDto
}