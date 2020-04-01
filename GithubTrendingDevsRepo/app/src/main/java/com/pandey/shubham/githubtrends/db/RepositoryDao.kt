package com.pandey.shubham.githubtrends.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pandey.shubham.githubtrends.repositories.data.RepositoriesDto
import io.reactivex.Flowable

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepositories(repositoryDbObject: RepositoriesDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRepositories(repositoryList: List<RepositoriesDto>)

    @Query("SELECT * FROM repositories")
    fun getAllRepositories(): LiveData<List<RepositoriesDto>>

    @Query("SELECT * FROM repositories WHERE repoName = :repoName")
    fun getRepository(repoName: String): RepositoriesDto

    @Query("SELECT COUNT(*) FROM repositories")
    fun getRepositoriesCount(): Flowable<Int>

}