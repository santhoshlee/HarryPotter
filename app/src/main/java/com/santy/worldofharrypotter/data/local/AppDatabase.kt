package com.santy.worldofharrypotter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.santy.worldofharrypotter.data.local.dao.BookDao
import com.santy.worldofharrypotter.data.local.entity.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}