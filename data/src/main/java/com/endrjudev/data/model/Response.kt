package com.endrjudev.data.model


import com.google.gson.annotations.SerializedName

internal data class Response(
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val items: List<Item>,
    @SerializedName("total_count") val totalCount: Int
)