package com.endrjudev.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.endrjudev.data.entity.RepositoryEntity

@Database(
    entities = [
        RepositoryEntity::class
    ],
    version = 1
)
internal abstract class GitHubDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}