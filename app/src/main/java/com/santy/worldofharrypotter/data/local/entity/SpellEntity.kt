package com.santy.worldofharrypotter.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "spells")
@JsonClass(generateAdapter = true)
data class SpellEntity(
    @PrimaryKey val index: Int,
    val spell: String,
    val use: String
)