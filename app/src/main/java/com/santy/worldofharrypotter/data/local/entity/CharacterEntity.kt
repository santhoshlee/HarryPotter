package com.santy.worldofharrypotter.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "character")
@JsonClass(generateAdapter = true)
data class CharacterEntity (
    @PrimaryKey val index: Int,
    val fullName: String,
    val nickname: String,
    val hogwartsHouse: String,
    val interpretedBy: String,
    val children: List<String>,
    val image: String,
    val birthdate: String
)