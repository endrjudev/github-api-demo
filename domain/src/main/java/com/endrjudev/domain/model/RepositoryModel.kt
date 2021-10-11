package com.endrjudev.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoryModel(
    val id: Int = 0,
    val description: String = "Description",
    val language: String? = "Java",
    val name: String = "Name",
    val ownerName: String = "Owner name",
    val ownerPicture: String,
    val score: Int = 5,
    val size: Int = 5,
    val cloneUrl: String = "https://picsum.photos/250",
    val watchersCount: Int = 5
) : Parcelable