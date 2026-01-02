package com.santy.worldofharrypotter.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.santy.worldofharrypotter.data.local.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    fun getBooks(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE `index` = :bookId")
    fun getBookById(bookId: Int): Flow<BookEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<BookEntity>)

}