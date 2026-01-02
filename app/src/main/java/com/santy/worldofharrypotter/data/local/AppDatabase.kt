package com.santy.worldofharrypotter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.santy.worldofharrypotter.data.local.converters.Converters
import com.santy.worldofharrypotter.data.local.dao.BookDao
import com.santy.worldofharrypotter.data.local.dao.CharacterDao
import com.santy.worldofharrypotter.data.local.dao.HouseDao
import com.santy.worldofharrypotter.data.local.dao.SpellDao
import com.santy.worldofharrypotter.data.local.entity.BookEntity
import com.santy.worldofharrypotter.data.local.entity.CharacterEntity
import com.santy.worldofharrypotter.data.local.entity.HouseEntity
import com.santy.worldofharrypotter.data.local.entity.SpellEntity

@Database(entities = [BookEntity::class, CharacterEntity::class, HouseEntity::class, SpellEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun characterDao(): CharacterDao
    abstract fun houseDao(): HouseDao
    abstract fun spellDao(): SpellDao
}