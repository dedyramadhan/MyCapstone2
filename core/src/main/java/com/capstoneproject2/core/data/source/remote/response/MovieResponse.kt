package com.capstoneproject2.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("title")
    val title: String? = null,
    @field:SerializedName("overview")
    val description: String? = null,
    @field:SerializedName("poster_path")
    val backgroundImage: String? = null,
    @field:SerializedName("backdrop_path")
    val landscapeImage: String? = null

)