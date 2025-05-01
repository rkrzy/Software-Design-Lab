package com.example.softwaredesign.week9.practice2

import java.io.Serializable

data class ReservedMovie(
    val _id: Int?,
    val name: String?,
    val poster_image: String?,
    val director: String?,
    val synopsis: String?,
    val reserved_time: String?
) : Serializable