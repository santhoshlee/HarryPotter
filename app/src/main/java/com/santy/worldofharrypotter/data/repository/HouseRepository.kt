package com.santy.worldofharrypotter.data.repository

import com.santy.worldofharrypotter.data.local.dao.HouseDao
import com.santy.worldofharrypotter.data.local.entity.HouseEntity
import com.santy.worldofharrypotter.data.remote.ApiService
import com.santy.worldofharrypotter.util.NetworkMonitor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HouseRepository @Inject constructor(
    private val api: ApiService,
    private val dao: HouseDao,
    private val network: NetworkMonitor
) {
    fun getHouses(): Flow<List<HouseEntity>> = flow {
        val local = dao.getHouses().first()
        if (local.isEmpty() && network.isConnected.value) {
            val remote = api.getHouses() // Make sure this is in your ApiService
            dao.insertAll(remote)
        }
        emitAll(dao.getHouses())
    }
}