package com.santy.worldofharrypotter.data.repository

import com.santy.worldofharrypotter.data.local.dao.SpellDao
import com.santy.worldofharrypotter.data.local.entity.SpellEntity
import com.santy.worldofharrypotter.data.remote.ApiService
import com.santy.worldofharrypotter.util.NetworkMonitor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SpellRepository @Inject constructor(
    private val api: ApiService,
    private val dao: SpellDao,
    private val network: NetworkMonitor
) {
    fun getSpells(): Flow<List<SpellEntity>> = flow {
        val local = dao.getSpells().first()
        if (local.isEmpty() && network.isConnected.value) {
            val remote = api.getSpells() // Ensure getSpells() is in your ApiService
            dao.insertAll(remote)
        }
        emitAll(dao.getSpells())
    }
}