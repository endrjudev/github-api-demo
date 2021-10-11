package com.endrjudev.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository")
internal data class RepositoryEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "language") val language: String?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "owner_name") val ownerName: String,
    @ColumnInfo(name = "owner_picture") val ownerPicture: String,
    @ColumnInfo(name = "score") val score: Int,
    @ColumnInfo(name = "size") val size: Int,
    @ColumnInfo(name = "clone_url") val cloneUrl: String,
    @ColumnInfo(name = "watchers_count") val watchersCount: Int
)