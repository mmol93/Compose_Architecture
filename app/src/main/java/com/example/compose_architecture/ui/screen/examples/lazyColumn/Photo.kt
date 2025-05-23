package com.example.compose_architecture.ui.screen.examples.lazyColumn

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("author")
    val author: String = "",
    @SerializedName("width")
    val width: Int = 0,
    @SerializedName("download_url")
    val downloadUrl: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("height")
    val height: Int = 0
)