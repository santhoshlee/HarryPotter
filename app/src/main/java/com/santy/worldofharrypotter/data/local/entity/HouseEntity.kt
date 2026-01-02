package com.santy.worldofharrypotter.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.squareup.moshi.JsonClass

@Entity(tableName = "houses")
@JsonClass(generateAdapter = true)
data class HouseEntity(
    @PrimaryKey val index: Int,
    val house: String,
    val emoji: String,
    val founder: String,
    val animal: String,
    val colors: List<String>
)
