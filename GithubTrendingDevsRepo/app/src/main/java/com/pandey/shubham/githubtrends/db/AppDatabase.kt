package com.pandey.shubham.githubtrends.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pandey.shubham.githubtrends.developers.data.DevelopersDto
import com.pandey.shubham.githubtrends.repositories.data.ContributorsDto
import com.pandey.shubham.githubtrends.repositories.data.RepositoriesDto

@Database(entities = [DevelopersDto::class, RepositoriesDto::class, ContributorsDto::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun developersDao(): DevelopersDao

    abstract fun repositoryDao(): RepositoryDao

    abstract fun contributorsDao(): ContributorsDao
}