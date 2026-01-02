package com.santy.worldofharrypotter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.santy.worldofharrypotter.data.local.entity.HouseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HouseDao {
    @Query("SELECT * FROM houses")
    fun getHouses(): Flow<List<HouseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<HouseEntity>)
}