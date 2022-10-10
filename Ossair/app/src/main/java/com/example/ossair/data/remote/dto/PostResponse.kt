package com.example.ossair.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostResponse (
    val dataSecond: String,
    val dataFirst: String
        )