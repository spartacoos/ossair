package com.example.ossair.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val dataSecond: String,
    val dataFirst: String
)