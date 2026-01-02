package com.santy.worldofharrypotter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.santy.worldofharrypotter.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    fun getCharacter(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM character WHERE `index` = :characterId")
    fun getCharacterById(characterId: Int): Flow<CharacterEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CharacterEntity>)

}