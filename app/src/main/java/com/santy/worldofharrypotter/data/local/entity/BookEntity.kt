package com.santy.worldofharrypotter.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "books")
@JsonClass(generateAdapter = true)
data class BookEntity (
    @PrimaryKey val index: Int,
    val number: Int,
    val title: String,
    val releaseDate: String,
    val description: String,
    val pages: Int,
    val cover: String,
)