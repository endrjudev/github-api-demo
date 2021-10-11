package com.endrjudev.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.endrjudev.data.entity.RepositoryEntity

@Dao
internal interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveItems(items: List<RepositoryEntity>)

    @Query("SELECT * FROM repository")
    suspend fun getItems(): List<RepositoryEntity>

    @Query("SELECT * FROM repository WHERE id LIKE :itemId LIMIT 1")
    suspend fun getItem(itemId: Int): RepositoryEntity
}