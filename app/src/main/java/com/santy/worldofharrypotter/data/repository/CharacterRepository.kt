package com.santy.worldofharrypotter.data.repository

import com.santy.worldofharrypotter.data.local.dao.CharacterDao
import com.santy.worldofharrypotter.data.local.entity.BookEntity
import com.santy.worldofharrypotter.data.local.entity.CharacterEntity
import com.santy.worldofharrypotter.data.remote.ApiService
import com.santy.worldofharrypotter.util.NetworkMonitor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val dao: CharacterDao,
    private val api: ApiService,
    private val network: NetworkMonitor
) {
    fun getCharacter(): Flow<List<CharacterEntity>> = flow {

        val local = dao.getCharacter().first()

        if (local.isEmpty() && network.isConnected.value) {
            val remote = api.getCharacter()
            dao.insertAll(remote)
        }

        emitAll(dao.getCharacter())
    }

    fun getCharacterById(characterId: Int): Flow<CharacterEntity?> {
        return dao.getCharacterById(characterId = characterId)
    }
}