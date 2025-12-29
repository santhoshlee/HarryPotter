package com.santy.worldofharrypotter.data.repository

import com.santy.worldofharrypotter.data.local.dao.BookDao
import com.santy.worldofharrypotter.data.local.entity.BookEntity
import com.santy.worldofharrypotter.data.remote.ApiService
import com.santy.worldofharrypotter.util.NetworkMonitor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val api: ApiService,
    private val dao: BookDao,
    private val network: NetworkMonitor
) {
    fun getBooks(): Flow<List<BookEntity>> = flow {

        val local = dao.getBooks().first()

        if (local.isEmpty() && network.isConnected.value) {
            val remote = api.getBooks()
            dao.insertAll(remote)
        }

        emitAll(dao.getBooks())
    }
}