package com.santy.worldofharrypotter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.santy.worldofharrypotter.data.local.entity.SpellEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpellDao {
    @Query("SELECT * FROM spells")
    fun getSpells(): Flow<List<SpellEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<SpellEntity>)
}